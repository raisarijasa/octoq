package com.mitrais.questionservice.dto;

import com.mitrais.questionservice.models.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionDto {
    private Long id;
    @NotNull(message = "{userId.notnull}")
    private String userId;
    @NotNull(message = "{title.notnull}")
    private String title;
    @NotNull(message = "{description.notnull}")
    private String description;
    private Status status;
}
