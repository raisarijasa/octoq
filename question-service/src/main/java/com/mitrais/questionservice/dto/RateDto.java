package com.mitrais.questionservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RateDto {
    @NotNull(message = "Post ID is mandatory")
    private Long postId;
    @NotNull(message = "User ID is mandatory")
    private String userId;
    private int rating;
}
