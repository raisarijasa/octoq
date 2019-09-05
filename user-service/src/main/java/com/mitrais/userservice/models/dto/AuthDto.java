package com.mitrais.userservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Provide Java object for authentication response.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@Data
@Builder
@AllArgsConstructor
public class AuthDto {
    private String email;
    private String token;
}
