package com.mitrais.questionservice.exceptions.model;

/**
 * Service exception
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
