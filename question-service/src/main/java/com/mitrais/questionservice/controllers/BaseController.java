package com.mitrais.questionservice.controllers;

import java.util.List;

import com.mitrais.questionservice.dto.Response;

/**
 * Provide Base Controller.
 *
 * @param <T> generic object
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public class BaseController<T> {

    /**
     * Provide generic response.
     *
     * @param code    message code
     * @param message message
     * @param data    generic data
     * @return response with generic object
     */
    Response<T> getResponse(String code, String message, List<T> data) {
        return new Response<T>()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    /**
     * Provide default response.
     *
     * @param code    message code
     * @param message message
     * @return response with generic object
     */
    Response getResponse(String code, String message) {
        return new Response()
                .setCode(code)
                .setMessage(message);
    }
}
