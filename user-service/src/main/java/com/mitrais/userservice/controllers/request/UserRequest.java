package com.mitrais.userservice.controllers.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

/**
 * Provide java object as User request.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@Data
@Builder
public class UserRequest {

    @NotEmpty(message = "{id.notEmpty}", groups = {GroupRequest.Update.class})
    private String id;
    @Email(message = "{email.invalid}", groups = {GroupRequest.Create.class, GroupRequest.Update.class})
    private String email;
    @NotEmpty(message = "{fullName.notEmpty}", groups = {GroupRequest.Create.class, GroupRequest.Update.class})
    private String fullname;
    @NotEmpty(message = "{password.notEmpty}", groups = {GroupRequest.Create.class})
    private String password;
    @NotEmpty(message = "{status.notEmpty}", groups = {GroupRequest.Update.class})
    private boolean enabled;
    @NotEmpty(message = "{roles.notEmpty}", groups = {GroupRequest.Create.class, GroupRequest.Update.class})
    private Set<String> roles;
}
