package com.mitrais.questionservice.controllers.requests;

import com.mitrais.questionservice.models.Status;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentRequest {
    private Long id;
    @NotEmpty(message = "{postId.NotEmpty}")
    private Long postId;
    @NotEmpty(message = "{userId.NotEmpty}")
    private String userId;
    @NotEmpty(message = "{description.NotEmpty}")
    private String description;
    private Status status;
}
