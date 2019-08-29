package com.mitrais.userservice.exceptions.model;

public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(final String message) {
        super(message);
    }

    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
