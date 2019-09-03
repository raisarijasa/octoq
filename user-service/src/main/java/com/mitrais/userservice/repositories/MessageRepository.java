package com.mitrais.userservice.repositories;

import org.springframework.beans.factory.annotation.Value;

/**
 * Provide message and code data
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
public class MessageRepository {
    // code 001xx for login
    @Value("${login.success.message}")
    public String AUTH_SUCCESS;
    public String AUTH_SUCCESS_CODE = "00101";

    @Value("${login.failed.message}")
    public String AUTH_FAIL;
    public String AUTH_FAIL_CODE = "00102";

    @Value("${unauthorized.message}")
    public String UNAUTHORIZED;
    public String UNAUTHORIZED_CODE = "00103";

    @Value("${token.expired.message}")
    public String TOKEN_EXPIRED;
    public String TOKEN_EXPIRED_CODE = "00104";

    @Value("${password.old.invalid.message}")
    public String INVALID_OLD_PASSWORD;
    public String INVALID_OLD_PASSWORD_CODE = "00105";

    // code 002xx for user
    @Value("${register.success.message}")
    public String USER_REG_SUCCESS;
    public String USER_REG_SUCCESS_CODE = "00201";

    @Value("${register.failed.message}")
    public String USER_REG_FAIL;
    public String USER_REG_FAIL_CODE = "00202";

    @Value("${user.exist.message}")
    public String USER_EXIST;
    public String USER_EXIST_CODE = "00203";

    @Value("${user.email.exist.message}")
    public String USER_WITH_EMAIL_EXIST;
    public String USER_WITH_EMAIL_EXIST_CODE = "00203";

    @Value("${user.not.found.message}")
    public String USER_NOT_FOUND;
    public String USER_NOT_FOUND_CODE = "00204";

    @Value("${user.email.not.found.message}")
    public String USER_WITH_EMAIL_NOT_FOUND;
    public String USER_WITH_EMAIL_NOT_FOUND_CODE = "00204";

    @Value("${user.delete.success.message}")
    public String USER_DEL_SUCCESS;
    public String USER_DEL_SUCCESS_CODE = "00205";

    @Value("${user.delete.failed.message}")
    public String USER_DEL_FAIL;
    public String USER_DEL_FAIL_CODE = "00206";

    @Value("${user.retrieve.success.message}")
    public String USER_GET_SUCCESS;
    public String USER_GET_SUCCESS_CODE = "00207";

    @Value("${user.update.success.message}")
    public String USER_UPDATE_SUCCESS;
    public String USER_UPDATE_SUCCESS_CODE = "00208";

    @Value("${user.not.active.message}")
    public String USER_NOT_ACTIVE;
    public String USER_NOT_ACTIVE_CODE = "00209";

    @Value("${password.change.success.message}")
    public String CHANGE_PASSWORD_SUCCESS;
    public String CHANGE_PASSWORD_SUCCESS_CODE = "00210";
}
