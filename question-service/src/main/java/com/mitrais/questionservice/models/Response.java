package com.mitrais.questionservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private boolean error;
    private String code;
    private String message;
    private List<T> data;
}
