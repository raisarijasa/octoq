package com.mitrais.userservice.controllers.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Provide Java object as Authentication request.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@Data
public class AuthRequest {
    @Email(message = "{email.invalid}")
    private String email;
    @NotEmpty(message = "{password.notEmpty}")
    private String password;
}
