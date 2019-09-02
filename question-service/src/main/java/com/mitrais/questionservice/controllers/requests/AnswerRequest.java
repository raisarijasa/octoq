package com.mitrais.questionservice.controllers.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Provide Answer request object.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
public class AnswerRequest {
    @NotNull(message = "{id.NotEmpty}", groups = {UpdateGroup.class})
    private Long id;
    @NotNull(message = "{questionId.NotEmpty}", groups = {UpdateGroup.class, CreateGroup.class})
    private Long questionId;
    @NotEmpty(message = "{userId.NotEmpty}", groups = {UpdateGroup.class, CreateGroup.class})
    private String userId;
    @NotEmpty(message = "{description.NotEmpty}", groups = {UpdateGroup.class, CreateGroup.class})
    private String description;

    public interface UpdateGroup {
    }

    public interface CreateGroup {
    }
}
