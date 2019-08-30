package com.mitrais.questionservice.controllers.requests;

import com.mitrais.questionservice.models.Status;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class QuestionRequest {
    private Long id;
    @NotEmpty(message = "{userId.NotEmpty}")
    private String userId;
    @NotEmpty(message = "{title.NotEmpty}")
    private String title;
    @NotEmpty(message = "{description.NotEmpty}")
    private String description;
    private Status status;
}
