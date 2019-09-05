package com.mitrais.userservice.controllers;

import java.util.List;

import com.mitrais.userservice.models.dto.Response;

/**
 * Provide based controller.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
public abstract class BaseController<T> {

    /**
     * Provide functionality to generate response.
     *
     * @param code    error code
     * @param message message
     * @param data    generic data
     * @return response object
     */
    Response<T> getResponse(String code, String message, List<T> data) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    /**
     * Provide functionality to generate response.
     *
     * @param code    error code
     * @param message message
     * @return response object
     */
    Response getResponse(String code, String message) {
        Response response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
}
