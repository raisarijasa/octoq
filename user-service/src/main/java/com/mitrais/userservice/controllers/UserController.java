package com.mitrais.userservice.controllers;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mitrais.userservice.controllers.request.ChangePasswordRequest;
import com.mitrais.userservice.controllers.request.GroupRequest;
import com.mitrais.userservice.controllers.request.UserRequest;
import com.mitrais.userservice.models.Authority;
import com.mitrais.userservice.models.Role;
import com.mitrais.userservice.models.User;
import com.mitrais.userservice.models.dto.UserDto;
import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.services.UserService;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Provide functionality to process user manipulation requests.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController<UserDto> {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final MessageRepository messageRepository;

    public UserController(UserService userService, MessageRepository messageRepository) {
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    /**
     * provide functionality to create an account
     *
     * @param request user data
     * @return response entity
     */
    @PostMapping()
    public ResponseEntity register(@Validated(GroupRequest.Create.class) @RequestBody UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user, "roles", "enabled");
        Set<Role> roles = setupRole(request.getRoles());
        if (roles == null) return invalidRoleResponse();
        user.setRoles(roles);
        try {
            userService.saveUser(user);
        } catch (DuplicateKeyException e) {
            return userEmailConflictResponse(request.getEmail());
        }

        return registerUserSuccessResponse();
    }

    /**
     * provide functionality to update account
     *
     * @param request user data
     * @return response entity
     */
    @PutMapping()
    public ResponseEntity updateUser(@Validated(GroupRequest.Update.class) @RequestBody UserRequest request, Principal principal) {
        User user = new User();
        BeanUtils.copyProperties(request, user, "roles");
        Set<Role> roles = setupRole(request.getRoles());
        if (roles == null) return invalidRoleResponse();
        user.setRoles(roles);
        userService.updateUser(user);
        return updateUserSuccessResponse();
    }

    /**
     * provide functionality to retrieve user data by email
     *
     * @param email user email address
     * @return response entity
     */
    @GetMapping("/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        List<UserDto> data = new ArrayList<>();
        data.add(userService.findUserByEmail(email));
        return getUserByEmailResponse(data);
    }

    /**
     * provide functionality to retrieve user data.
     *
     * @return response entity
     */
    @GetMapping()
    public ResponseEntity getUsers() {
        return getUserByEmailResponse(userService.findUsers());
    }

    /**
     * provide functionality to delete user data by email
     *
     * @param email user email address
     * @return response entity
     */
    @DeleteMapping("/{email}")
    public ResponseEntity deleteUserByEmail(@PathVariable String email) {
        if (email == null || email.isEmpty()) {
            return emailEmptyResponse();
        }
        userService.deleteUserByEmail(email);
        return deleteUserByEmailResponse();
    }

    /**
     * provide functionality to set enable user account
     *
     * @param email  user email address
     * @param status user status
     * @return response entity
     */
    @PutMapping("/activation/{email}/{status}")
    public ResponseEntity enableUser(@PathVariable String email, @PathVariable boolean status) {
        User user = new User();
        user.setEmail(email);
        user.setEnabled(status);
        userService.enableUser(user);
        return updateUserSuccessResponse();
    }

    /**
     * Provide functionality to change user password.
     *
     * @param passwordRequest password object
     * @return response entity
     */
    @PostMapping("/change-password")
    public ResponseEntity changePassword(@Valid @RequestBody ChangePasswordRequest passwordRequest) {
        userService.changeUserPassword(passwordRequest.getEmail(), passwordRequest.getNewPassword(), passwordRequest.getOldPassword());
        return changePasswordSuccessResponse();
    }

    private Set<Role> setupRole(Set<String> stringRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : stringRoles) {
            if (role.equalsIgnoreCase(Authority.ADMIN.getAuthority()) ||
                    role.equalsIgnoreCase(Authority.USER.getAuthority())) {
                roles.add(new Role("", role));
            } else {
                return null;
            }
        }
        return roles;
    }

    private ResponseEntity registerUserSuccessResponse() {
        return ok(getResponse(
                messageRepository.USER_REG_SUCCESS_CODE,
                messageRepository.USER_REG_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity userEmailConflictResponse(String email) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(getResponse(
                messageRepository.USER_WITH_EMAIL_EXIST_CODE,
                String.format(messageRepository.USER_WITH_EMAIL_EXIST, email)
        ));
    }

    private ResponseEntity invalidRoleResponse() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponse(
                messageRepository.ROLE_INVALID_CODE,
                messageRepository.ROLE_INVALID
        ));
    }

    private ResponseEntity updateUserSuccessResponse() {
        return ok(getResponse(
                messageRepository.USER_UPDATE_SUCCESS_CODE,
                messageRepository.USER_UPDATE_SUCCESS,
                new ArrayList<>()
        ));
    }

    private ResponseEntity getUserByEmailResponse(List<UserDto> data) {
        return ok(getResponse(
                messageRepository.USER_GET_SUCCESS_CODE,
                messageRepository.USER_GET_SUCCESS,
                data
        ));
    }

    private ResponseEntity deleteUserByEmailResponse() {
        return ok(getResponse(
                messageRepository.USER_DEL_SUCCESS_CODE,
                messageRepository.USER_DEL_SUCCESS
        ));
    }

    private ResponseEntity emailEmptyResponse() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getResponse(
                messageRepository.EMAIL_EMPTY_CODE,
                messageRepository.EMAIL_EMPTY
        ));
    }

    private ResponseEntity changePasswordSuccessResponse() {
        return ok(getResponse(
                messageRepository.CHANGE_PASSWORD_SUCCESS_CODE,
                messageRepository.CHANGE_PASSWORD_SUCCESS
        ));
    }
}
