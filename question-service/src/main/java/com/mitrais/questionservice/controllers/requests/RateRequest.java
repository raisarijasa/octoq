package com.mitrais.questionservice.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RateRequest {
    @NotEmpty(message = "{postId.NotEmpty}")
    private Long postId;
    @NotEmpty(message = "{userId.NotEmpty}")
    private String userId;
    private int rating;
}
