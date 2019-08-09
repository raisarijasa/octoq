package com.mitrais.userservice.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AuthBody {
    private String email;
    private String password;
}
