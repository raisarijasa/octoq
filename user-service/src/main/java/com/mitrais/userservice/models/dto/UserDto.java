package com.mitrais.userservice.models.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Provide Java object for user response.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String fullname;
    private Boolean enabled;
    private Set<String> roles;
}
