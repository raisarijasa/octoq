package com.mitrais.questionservice.controllers.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import com.mitrais.questionservice.models.Status;

/**
 * Provide Comment request object.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
public class CommentRequest {
    @NotNull(message = "{id.NotEmpty}", groups = {UpdateGroup.class})
    private Long id;
    @NotNull(message = "{postId.NotEmpty}", groups = {UpdateGroup.class, CreateGroup.class})
    private Long postId;
    @NotEmpty(message = "{userId.NotEmpty}", groups = {UpdateGroup.class, CreateGroup.class})
    private String userId;
    @NotEmpty(message = "{description.NotEmpty}", groups = {UpdateGroup.class, CreateGroup.class})
    private String description;
    private Status status;

    public interface UpdateGroup {
    }

    public interface CreateGroup {
    }
}
