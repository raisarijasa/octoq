package com.mitrais.questionservice.controllers;

import java.util.List;

import com.mitrais.questionservice.dto.Response;

/**
 * Provide Base Controller.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 * @param <T> generic object
 */
public class BaseController<T> {

    /**
     * Get Response
     *
     * @param error   error status
     * @param code    message code
     * @param message message
     * @param data    generic data
     * @return response with generic object
     */
    Response<T> getResponse(boolean error, String code, String message, List<T> data) {
        return new Response<T>()
                .setError(error)
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
