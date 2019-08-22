package com.mitrais.questionservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {
    private Long id;
    @NotNull(message = "Question ID is mandatory")
    private Long questionId;
    @NotNull(message = "User ID is mandatory")
    private String userId;
    @NotNull(message = "Description is mandatory")
    private String description;
}
