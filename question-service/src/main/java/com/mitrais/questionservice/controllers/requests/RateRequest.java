package com.mitrais.questionservice.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RateRequest {
    @NotNull(message = "{postId.NotEmpty}")
    private Long postId;
    @NotEmpty(message = "{userId.NotEmpty}")
    private String userId;
    private int rating;
}
