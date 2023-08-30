package com.nucleusteq.asessmentPlatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BadCredentialsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadCredentialsException(String message) {
        super(message);
    }
}
