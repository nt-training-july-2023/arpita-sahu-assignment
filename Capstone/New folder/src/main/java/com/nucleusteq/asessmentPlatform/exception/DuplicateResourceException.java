package com.nucleusteq.asessmentPlatform.exception;

public class DuplicateResourceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public DuplicateResourceException(final String message) {
        super(message);
    }

}
