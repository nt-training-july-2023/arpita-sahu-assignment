package com.nucleusteq.asessmentPlatform.exception;

/**
 * Custom exception indicating that a category was not found. This exception is
 * typically thrown when attempting to retrieve a category that does not exist.
 */
public final class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new CategoryNotFoundException with the specified detail
     * message.
     * 
     * @param message The detail message.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
