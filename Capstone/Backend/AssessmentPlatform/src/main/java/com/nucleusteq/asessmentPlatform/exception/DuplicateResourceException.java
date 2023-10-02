package com.nucleusteq.asessmentPlatform.exception;

/**
 * Exception thrown when attempting to create or add a duplicate resource.
 */
public class DuplicateResourceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DuplicateResourceException with the specified detail
     * message.
     * @param message The detail message explaining the reason for the
     *                exception.
     */
    public DuplicateResourceException(final String message) {
        super(message);
    }
}
