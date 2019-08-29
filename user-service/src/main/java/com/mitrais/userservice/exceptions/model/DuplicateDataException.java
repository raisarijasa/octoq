package com.mitrais.userservice.exceptions.model;

public class DuplicateDataException extends RuntimeException {

    public DuplicateDataException() {
        super();
    }

    public DuplicateDataException(final String message) {
        super(message);
    }

    public DuplicateDataException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
