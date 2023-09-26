package com.nucleusteq.asessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception indicating that a user was not found. This exception is
 * typically thrown when attempting to retrieve a user that does not exist.
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public final class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     * @param message The detail message.
     */
    public UserNotFoundException(final String message) {
        super(message);
    }
}
