package com.mitrais.userservice.models.dto;

import lombok.Data;

@Data
public class AuthBody {
    private String email;
    private String password;
}
