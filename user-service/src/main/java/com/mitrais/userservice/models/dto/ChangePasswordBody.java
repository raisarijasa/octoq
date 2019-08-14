package com.mitrais.userservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordBody {
    private String oldPassword;
    private String newPassword;
}
