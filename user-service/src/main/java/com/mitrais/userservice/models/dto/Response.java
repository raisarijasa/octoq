package com.mitrais.userservice.models.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Provide generic response.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private boolean error;
    private String code;
    private String message;
    private List<T> data;
}
