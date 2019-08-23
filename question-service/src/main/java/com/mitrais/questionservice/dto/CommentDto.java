package com.mitrais.questionservice.dto;

import com.mitrais.questionservice.models.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    private Long id;
    @NotNull(message = "{postId.notnull}")
    private Long postId;
    @NotNull(message = "{userId.notnull}")
    private String userId;
    @NotNull(message = "{description.notnull}")
    private String description;
    private Status status;
}
