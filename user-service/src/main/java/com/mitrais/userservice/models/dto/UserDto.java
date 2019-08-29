package com.mitrais.userservice.models.dto;

import com.mitrais.userservice.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String password;
    private String fullname;
    private Boolean enabled;
    private Set<String> roles;
}
