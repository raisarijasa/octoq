package com.mitrais.userservice.controllers;

import com.mitrais.userservice.exceptions.model.ServiceException;
import com.mitrais.userservice.exceptions.model.UserNotFoundException;
import com.mitrais.userservice.models.Authority;
import com.mitrais.userservice.models.Role;
import com.mitrais.userservice.models.User;
import com.mitrais.userservice.models.dto.ChangePasswordBody;
import com.mitrais.userservice.models.dto.Response;
import com.mitrais.userservice.models.dto.UserResponse;
import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class UserController implements BaseResponse<UserResponse> {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/user")
    public ResponseEntity register(@RequestBody User user) throws ServiceException {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            throw new ServiceException(String.format(MessageRepository.USER_WITH_EMAIL_EXIST, user.getEmail()), "register service");
        }
        log.info("UserController register " + user);
        userService.saveUser(user);
        return ok(getResponse(
                false,
                MessageRepository.USER_REG_SUCCESS_CODE,
                MessageRepository.USER_REG_SUCCESS,
                new ArrayList<>()
        ));
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@RequestBody User user, Principal principal) {
//        if (principal != null) {
//            User loginUser = userService.findUserByEmail(principal.getName());
            User userExists;

//            if (loginUser.getEmail().equalsIgnoreCase(user.getEmail())) {
//                userExists = loginUser;
//            } else if (isAdmin(loginUser.getRoles())) {
            userExists = userService.findUserByEmail(user.getEmail());
//            } else {
//                throw new BadCredentialsException(MessageRepository.UNAUTHORIZED);
//            }

        if (userExists == null) {
            throw new UserNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, user.getEmail()));
        }
        log.info("UserController updateUser " + user);
        userExists.setEmail(user.getEmail());
        userExists.setFullname(user.getFullname());
        userExists.setRoles(user.getRoles());
        userService.saveUser(userExists);
        return ok(getResponse(
                false,
                MessageRepository.USER_UPDATE_SUCCESS_CODE,
                MessageRepository.USER_UPDATE_SUCCESS,
                new ArrayList<>()
        ));
//        } else {
//            throw new BadCredentialsException(MessageRepository.UNAUTHORIZED);
//        }
    }

    @GetMapping("/user")
    public ResponseEntity getUserByEmail(@RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, email));
        }
        log.info("UserController getUserByEmail " + user);
        Set<String> roles = new HashSet<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRole());
        }
        List<UserResponse> data = new ArrayList<>();
        data.add(new UserResponse(user.getId(), user.getEmail(), user.getFullname(), roles));
        return ok(getResponse(
                false,
                MessageRepository.USER_GET_SUCCESS_CODE,
                MessageRepository.USER_GET_SUCCESS,
                data
        ));
    }

    @DeleteMapping("/user/{email}")
    public ResponseEntity deleteUserByEmail(@PathVariable String email) {
        User user = userService.findUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, email));
        }
        log.info("UserController deleteUserByEmail " + user);
        userService.deleteUser(user);
        return ok(getResponse(
                false,
                MessageRepository.USER_DEL_SUCCESS_CODE,
                MessageRepository.USER_DEL_SUCCESS,
                new ArrayList<>()
        ));
    }

    @PutMapping("/user/activation")
    public ResponseEntity enableUser(@RequestBody User user) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists == null) {
            throw new UserNotFoundException(String.format(MessageRepository.USER_WITH_EMAIL_NOT_FOUND, user.getEmail()));
        }
        log.info("UserController enableUser " + userExists);
        userExists.setEnabled(user.isEnabled());
        userService.saveUser(userExists);
        return ok(getResponse(
                false,
                MessageRepository.USER_UPDATE_SUCCESS_CODE,
                MessageRepository.USER_UPDATE_SUCCESS,
                new ArrayList<>()
        ));
    }

    @PostMapping("/user/change-password")
    public ResponseEntity changePassword(@RequestBody ChangePasswordBody passwordBody, Principal principal) {
        User user = userService.findUserByEmail(principal.getName());
        if (user == null) {
            throw new UserNotFoundException(MessageRepository.USER_NOT_FOUND);
        }
        if (!userService.isOldPasswordValid(user.getPassword(), passwordBody.getOldPassword())) {
            throw new BadCredentialsException(MessageRepository.INVALID_OLD_PASSWORD);
        }
        userService.changeUserPassword(user, passwordBody.getNewPassword());
        return ok(getResponse(
                false,
                MessageRepository.CHANGE_PASSWORD_SUCCESS_CODE,
                MessageRepository.CHANGE_PASSWORD_SUCCESS,
                new ArrayList<>()
        ));
    }

    private boolean isAdmin(Set<Role> roles) {
        for (Role role : roles) {
            if (role.getRole().equalsIgnoreCase(Authority.ADMIN.getAuthority())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Response<UserResponse> getResponse(boolean error, String code, String message, List<UserResponse> data) {
        Response<UserResponse> response = new Response<>();
        response.setError(error);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
