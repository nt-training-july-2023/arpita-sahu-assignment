package com.nucleusteq.asessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception indicating bad credentials during authentication. This
 * exception is annotated with {@link ResponseStatus} to indicate a HTTP 409
 * Conflict status when thrown.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public final class BadCredentialsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new BadCredentialsException with the specified detail
     * message.
     * @param message The detail message.
     */
    public BadCredentialsException(String message) {
        super(message);
    }
}

