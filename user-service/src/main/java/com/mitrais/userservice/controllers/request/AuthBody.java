package com.mitrais.userservice.controllers.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class AuthBody {
    @Email(message = "{email.invalid}")
    private String email;
    @NotEmpty(message = "{password.notEmpty}")
    private String password;
}
