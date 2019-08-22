package com.mitrais.questionservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {
    private boolean error;
    private String code;
    private String message;
    private List<T> data;
}
