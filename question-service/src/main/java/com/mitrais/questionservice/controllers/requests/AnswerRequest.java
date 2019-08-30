package com.mitrais.questionservice.controllers.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnswerRequest {
    private Long id;
    @NotNull(message = "{questionId.NotEmpty}")
    private Long questionId;
    @NotEmpty(message = "{userId.NotEmpty}")
    private String userId;
    @NotEmpty(message = "{description.NotEmpty}")
    private String description;
}
