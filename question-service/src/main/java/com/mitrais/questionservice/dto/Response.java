package com.mitrais.questionservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Provide java object which will be use as default response object.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String code;
    private String message;
    private List<T> data;
}
