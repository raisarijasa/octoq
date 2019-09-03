package com.mitrais.userservice.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mitrais.userservice.models.User;
import com.mitrais.userservice.models.dto.UserDto;

/**
 * Provide functionality to manipulate User data.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
public interface UserService extends UserDetailsService {

    /**
     * Provide functionality to get user by email
     *
     * @param email user email address
     * @return User Dto
     */
    UserDto findUserByEmail(String email);

    /**
     * provide functionality to save user data
     *
     * @param user object
     */
    void saveUser(User user);

    /**
     * provide functionality to delete user data by email
     *
     * @param email user email address
     */
    void deleteUserByEmail(String email);

    /**
     * provide validation user password
     *
     * @param encryptedPassword encrypted user password
     * @param oldPassword       user password
     * @return boolean
     */
    boolean isOldPasswordValid(String encryptedPassword, String oldPassword);

    /**
     * provide functionality to change user password
     *
     * @param email       user email address
     * @param passwordNew new password
     * @param passwordOld old password
     */
    void changeUserPassword(String email, String passwordNew, String passwordOld);

    /**
     * provide functionality to update user data
     *
     * @param user object
     */
    void updateUser(User user);

    /**
     * provide functionality to set user status
     *
     * @param user object
     */
    void enableUser(User user);
}
