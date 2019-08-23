package com.mitrais.questionservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {
    private Long id;
    @NotNull(message = "{questionId.notnull}")
    private Long questionId;
    @NotNull(message = "{userId.notnull}")
    private String userId;
    @NotNull(message = "{description.notnull}")
    private String description;
}
