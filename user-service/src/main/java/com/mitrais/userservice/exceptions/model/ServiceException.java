package com.mitrais.userservice.exceptions.model;

/**
 * Provide functionality to receive service exception.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
