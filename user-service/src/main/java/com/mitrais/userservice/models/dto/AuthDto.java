package com.mitrais.userservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthDto {
    private String email;
    private String token;
}
