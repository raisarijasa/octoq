package com.mitrais.userservice.services;

import com.mitrais.userservice.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUserByEmail(String email);

    void saveUser(User user);

    void deleteUser(User user);

    boolean isOldPasswordValid(String encryptedPassword, String oldPassword);

    void changeUserPassword(User user, String password);
}
