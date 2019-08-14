package com.mitrais.questionservice.controllers;

import com.mitrais.questionservice.models.Response;

import java.util.List;

public interface BaseController<T> {
    Response<T> getResponse(boolean error, String code, String message, List<T> data);
}
