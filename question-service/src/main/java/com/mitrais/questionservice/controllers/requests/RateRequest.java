package com.mitrais.questionservice.controllers.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Provide Rate request object.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
public class RateRequest {
    @NotNull(message = "{postId.NotEmpty}")
    private Long postId;
    @NotEmpty(message = "{userId.NotEmpty}")
    private String userId;
    private int rating;
}
