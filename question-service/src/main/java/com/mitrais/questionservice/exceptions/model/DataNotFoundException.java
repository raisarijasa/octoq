package com.mitrais.questionservice.exceptions.model;

/**
 * Data not found exception
 */
public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(final String message) {
        super(message);
    }

    public DataNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
