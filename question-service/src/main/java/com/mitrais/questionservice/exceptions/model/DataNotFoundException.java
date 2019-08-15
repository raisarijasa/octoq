package com.mitrais.questionservice.exceptions.model;

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
