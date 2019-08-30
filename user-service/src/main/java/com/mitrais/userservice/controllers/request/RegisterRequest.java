package com.mitrais.userservice.controllers.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Builder
public class RegisterRequest {
    private String id;
    @Email(message = "{email.invalid}")
    private String email;
    @NotEmpty(message = "{password.notEmpty}")
    private String password;
    @NotEmpty(message = "{fullName.notEmpty}")
    private String fullname;
    private Boolean enabled;
    @NotEmpty(message = "{roles.notEmpty}")
    private Set<String> roles;
}
