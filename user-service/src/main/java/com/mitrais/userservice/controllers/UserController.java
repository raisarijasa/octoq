package com.mitrais.userservice.controllers;

import com.mitrais.userservice.controllers.request.ChangePasswordRequest;
import com.mitrais.userservice.controllers.request.RegisterRequest;
import com.mitrais.userservice.controllers.request.UserRequest;
import com.mitrais.userservice.exceptions.model.DuplicateDataException;
import com.mitrais.userservice.exceptions.model.ServiceException;
import com.mitrais.userservice.models.Authority;
import com.mitrais.userservice.models.Role;
import com.mitrais.userservice.models.dto.Response;
import com.mitrais.userservice.models.dto.UserDto;
import com.mitrais.userservice.repositories.MessageRepository;
import com.mitrais.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
public class UserController implements BaseResponse<UserDto> {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity register(@Valid @RequestBody RegisterRequest user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        try {
            userService.saveUser(userDto);
        } catch (DuplicateKeyException e) {
            throw new DuplicateDataException("User with email " + user.getEmail() + " already exist.");
        } catch (Exception e) {
            throw new ServiceException("{default.error.message}");
        }
        return ok(getResponse(
                false,
                MessageRepository.USER_REG_SUCCESS_CODE,
                MessageRepository.USER_REG_SUCCESS,
                new ArrayList<>()
        ));
    }

    @PutMapping("/")
    public ResponseEntity updateUser(@Valid @RequestBody UserRequest userRequest, Principal principal) {
        UserDto user = new UserDto();
        BeanUtils.copyProperties(userRequest, user);
        userService.updateUser(user);
        return ok(getResponse(
                false,
                MessageRepository.USER_UPDATE_SUCCESS_CODE,
                MessageRepository.USER_UPDATE_SUCCESS,
                new ArrayList<>()
        ));
    }

    @GetMapping("/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        List<UserDto> data = new ArrayList<>();
        data.add(userService.findUserByEmail(email));
        return ok(getResponse(
                false,
                MessageRepository.USER_GET_SUCCESS_CODE,
                MessageRepository.USER_GET_SUCCESS,
                data
        ));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity deleteUserByEmail(@PathVariable String email) {
        if (email == null || email.isEmpty()) {
            throw new ServiceException("{email.notEmpty}");
        }
        userService.deleteUserByEmail(email);
        return ok(getResponse(
                false,
                MessageRepository.USER_DEL_SUCCESS_CODE,
                MessageRepository.USER_DEL_SUCCESS,
                new ArrayList<>()
        ));
    }

    @PutMapping("/activation")
    public ResponseEntity enableUser(@RequestBody UserRequest user) {
        if (user.getEnabled() == null) {
            throw new ServiceException("{status.notEmpty}");
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userService.enableUser(userDto);
        return ok(getResponse(
                false,
                MessageRepository.USER_UPDATE_SUCCESS_CODE,
                MessageRepository.USER_UPDATE_SUCCESS,
                new ArrayList<>()
        ));
    }

    @PostMapping("/change-password")
    public ResponseEntity changePassword(@Valid @RequestBody ChangePasswordRequest passwordBody, Principal principal) {
        userService.changeUserPassword(principal.getName(), passwordBody.getNewPassword(), passwordBody.getOldPassword());
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
    public Response<UserDto> getResponse(boolean error, String code, String message, List<UserDto> data) {
        Response<UserDto> response = new Response<>();
        response.setError(error);
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
