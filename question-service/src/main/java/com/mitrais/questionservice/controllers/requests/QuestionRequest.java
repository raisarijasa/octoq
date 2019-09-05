package com.mitrais.questionservice.controllers.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

import com.mitrais.questionservice.models.Status;

/**
 * Provide Question request object.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
@Builder
public class QuestionRequest {
    @NotNull(message = "{id.NotEmpty}", groups = {GroupRequest.Update.class})
    private Long id;
    @NotEmpty(message = "{userId.NotEmpty}", groups = {GroupRequest.Update.class, GroupRequest.Create.class})
    private String userId;
    @NotEmpty(message = "{title.NotEmpty}", groups = {GroupRequest.Update.class, GroupRequest.Create.class})
    private String title;
    @NotEmpty(message = "{description.NotEmpty}", groups = {GroupRequest.Update.class, GroupRequest.Create.class})
    private String description;
    private Status status;
}
