package com.mitrais.questionservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RateDto {
    @NotNull(message = "{postId.notnull}")
    private Long postId;
    @NotNull(message = "{userId.notnull}")
    private String userId;
    private int rating;
}
