package com.mitrais.userservice.exceptions.model;

/**
 * Provide functionality to receive duplicate data exception.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
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
