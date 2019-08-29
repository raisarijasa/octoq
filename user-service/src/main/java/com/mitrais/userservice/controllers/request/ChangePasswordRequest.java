package com.mitrais.userservice.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {
    @NotEmpty(message = "{oldPassword.notEmpty}")
    private String oldPassword;
    @NotEmpty(message = "{newPassword.notEmpty}")
    private String newPassword;
}
