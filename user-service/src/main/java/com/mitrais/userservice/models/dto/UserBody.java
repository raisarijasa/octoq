package com.mitrais.userservice.models.dto;

import com.mitrais.userservice.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBody {
    private String email;
    private String password;
    private String fullname;
    private boolean enabled;
    private Set<Role> roles;
}
