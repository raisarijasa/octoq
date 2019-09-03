package com.mitrais.userservice.exceptions.model;

/**
 * Provide functionality to receive user not found exception.
 *
 * @author Rai Suardhyana Arijasa on 9/3/2019.
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(final String message) {
        super(message);
    }

    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
