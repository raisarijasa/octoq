package com.mitrais.questionservice.dto;

import com.mitrais.questionservice.models.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QuestionDto {
    private Long id;
    @NotNull(message = "User ID is mandatory")
    private String userId;
    @NotNull(message = "Title is mandatory")
    private String title;
    @NotNull(message = "Description is mandatory")
    private String description;
    private Status status;
}
