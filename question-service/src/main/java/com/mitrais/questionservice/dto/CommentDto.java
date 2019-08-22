package com.mitrais.questionservice.dto;

import com.mitrais.questionservice.models.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    private Long id;
    @NotNull(message = "Post ID is mandatory")
    private Long postId;
    @NotNull(message = "User ID is mandatory")
    private String userId;
    @NotNull(message = "Description is mandatory")
    private String description;
    private Status status;
}
