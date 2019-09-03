package com.mitrais.userservice.controllers.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Provide Java object as Change password request.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@Data
@AllArgsConstructor
public class ChangePasswordRequest {
    @Email(message = "{email.invalid}")
    private String email;
    @NotEmpty(message = "{oldPassword.notEmpty}")
    private String oldPassword;
    @NotEmpty(message = "{newPassword.notEmpty}")
    private String newPassword;
}
