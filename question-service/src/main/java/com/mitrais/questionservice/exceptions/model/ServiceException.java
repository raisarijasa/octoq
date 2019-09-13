package com.mitrais.questionservice.exceptions.model;

/**
 * Provide functionality to manipulate General Service Exception.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
        super();
    }

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
