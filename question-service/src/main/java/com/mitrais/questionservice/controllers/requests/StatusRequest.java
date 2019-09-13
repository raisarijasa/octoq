package com.mitrais.questionservice.controllers.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import com.mitrais.questionservice.models.Status;

/**
 * Provide status request object.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
public class StatusRequest {
    @NotNull(message = "{postId.NotEmpty}")
    private Long id;
    @NotEmpty(message = "{status.NotEmpty}")
    private Status status;
}
