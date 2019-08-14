package com.mitrais.userservice.controllers;

import com.mitrais.userservice.models.dto.Response;

import java.util.List;

public interface BaseResponse<T> {
    Response<T> getResponse(boolean error, String code, String message, List<T> data);
}
