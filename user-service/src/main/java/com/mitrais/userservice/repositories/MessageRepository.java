package com.mitrais.userservice.repositories;

public class MessageRepository {
    // code 001xx for login
    public static final String AUTH_SUCCESS_CODE = "00101";
    public static final String AUTH_SUCCESS = "Login Success";
    public static final String AUTH_FAIL_CODE = "00102";
    public static final String AUTH_FAIL = "Invalid email or password";
    public static final String UNAUTHORIZED_CODE = "00103";
    public static final String UNAUTHORIZED = "You are not authorized to perform this action";
    public static final String TOKEN_EXPIRED_CODE = "00104";
    public static final String TOKEN_EXPIRED = "Token expired or invalid, please try to re-login";
    public static final String INVALID_OLD_PASSWORD_CODE = "00105";
    public static final String INVALID_OLD_PASSWORD = "Invalid old password";

    // code 002xx for user
    public static final String USER_REG_SUCCESS_CODE = "00201";
    public static final String USER_REG_SUCCESS = "User registered successfully";
    public static final String USER_REG_FAIL_CODE = "00202";
    public static final String USER_REG_FAIL = "User registered successfully";
    public static final String USER_EXIST_CODE = "00203";
    public static final String USER_EXIST = "User already exist";
    public static final String USER_WITH_EMAIL_EXIST = "User with email: %s already exist";
    public static final String USER_NOT_FOUND_CODE = "00204";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_WITH_EMAIL_NOT_FOUND = "User with email: %s not found";
    public static final String USER_DEL_SUCCESS_CODE = "00205";
    public static final String USER_DEL_SUCCESS = "User deleted successfully";
    public static final String USER_DEL_FAIL_CODE = "00206";
    public static final String USER_DEL_FAIL = "Delete user failed";
    public static final String USER_GET_SUCCESS_CODE = "00207";
    public static final String USER_GET_SUCCESS = "User retrieved successfully";
    public static final String USER_UPDATE_SUCCESS_CODE = "00208";
    public static final String USER_UPDATE_SUCCESS = "User updated successfully";
    public static final String USER_NOT_ACTIVE_CODE = "00209";
    public static final String USER_NOT_ACTIVE = "Your account has not been activated yet, please contact administrator.";
    public static final String CHANGE_PASSWORD_SUCCESS_CODE = "00210";
    public static final String CHANGE_PASSWORD_SUCCESS = "Your password has been changed, please re-login to get new access token.";
}
