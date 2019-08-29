package com.mitrais.userservice.services;

import com.mitrais.userservice.models.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto findUserByEmail(String email);

    void saveUser(UserDto user);

    void deleteUserByEmail(String email);

    boolean isOldPasswordValid(String encryptedPassword, String oldPassword);

    void changeUserPassword(String email, String passwordNew, String passwordOld);

    void updateUser(UserDto userDto);

    void enableUser(UserDto userDto);
}
