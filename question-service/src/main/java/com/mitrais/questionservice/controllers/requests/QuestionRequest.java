package com.mitrais.questionservice.controllers.requests;

import com.mitrais.questionservice.models.Status;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class QuestionRequest {
    private Long id;
    @NotNull(message = "{userId.notnull}")
    private String userId;
    @NotNull(message = "{title.notnull}")
    private String title;
    @NotNull(message = "{description.notnull}")
    private String description;
    private Status status;
}
