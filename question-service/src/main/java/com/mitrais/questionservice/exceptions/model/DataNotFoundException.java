package com.mitrais.questionservice.exceptions.model;

/**
 * Provide functionality to manipulate Data Not Found Exception.
 *
 * @author Rai Suardhyana Arijasa on 9/2/2019.
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
