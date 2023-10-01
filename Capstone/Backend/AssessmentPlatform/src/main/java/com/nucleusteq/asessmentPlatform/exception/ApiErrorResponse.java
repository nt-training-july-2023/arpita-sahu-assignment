package com.nucleusteq.asessmentPlatform.exception;


/**
 * The `ApiErrorResponse` class represents an error response in the API. It
 * includes the HTTP status code and a message describing the error.
 */
public class ApiErrorResponse {

    /**
     * The error message associated with the error response.
     */
    private String message;
    /**
     * The HTTP status code of the error response.
     */
    private Integer status;

    /**
     * Gets the HTTP status code of the error response.
     *
     * @return The HTTP status code.
     */
    public final Integer getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code of the error response.
     *
     * @param errorStatus The HTTP status code to set.
     */
    public final void setStatus(final Integer errorStatus) {
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
    public ApiErrorResponse(final String errorMessage, 
            final Integer errorStatus) {
        super();
        this.message = errorMessage;
        this.status = errorStatus;
}

    /**
     * Constructs an empty `ApiErrorResponse` object. This constructor is
     * provided for convenience.
     **/
    public ApiErrorResponse() {
        super();
    }
}

