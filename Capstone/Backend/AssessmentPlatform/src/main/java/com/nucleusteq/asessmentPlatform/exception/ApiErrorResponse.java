package com.nucleusteq.asessmentPlatform.exception;

import org.springframework.http.HttpStatus;

/**
 * The `ApiErrorResponse` class represents an error response in the API. It
 * includes the HTTP status code and a message describing the error.
 */
public class ApiErrorResponse {

    /**
     * The HTTP status code of the error response.
     */
    private HttpStatus status;

    /**
     * The error message associated with the error response.
     */
    private String message;

    /**
     * Gets the HTTP status code of the error response.
     *
     * @return The HTTP status code.
     */
    public final HttpStatus getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code of the error response.
     *
     * @param errorStatus The HTTP status code to set.
     */
    public final void setStatus(final HttpStatus errorStatus) {
        this.status = errorStatus;
    }

    /**
     * Gets the error message associated with the error response.
     *
     * @return The error message.
     */
    public final String getMessage() {
        return message;
    }

    /**
     * Sets the error message for the error response.
     *
     * @param errorMessage The error message to set.
     */
    public final void setMessage(final String errorMessage) {
        this.message = errorMessage;
    }

    /**
     * Constructs an `ApiErrorResponse` object with the provided HTTP status
     * code and message.
     *
     * @param errorStatus  The HTTP status code for the error response.
     * @param errorMessage The error message describing the error.
     */
    public ApiErrorResponse(final HttpStatus errorStatus,
            final String errorMessage) {
        super();
        this.status = errorStatus;
        this.message = errorMessage;
    }

    /**
     * Constructs an empty `ApiErrorResponse` object. This constructor is
     * provided for convenience.
     **/
    public ApiErrorResponse() {
        super();
    }
}

