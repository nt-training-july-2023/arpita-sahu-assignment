package com.nucleusteq.asessmentPlatform.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler is a Spring framework controller advice class that
 * handles exceptions globally for the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException and returns an appropriate response.
     *
     * @param ex The ResourceNotFoundException to be handled.
     * @return A ResponseEntity containing the error response for
     *         ResourceNotFoundException.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ApiErrorResponse>
                   resourceNotFoundExceptionHandler(
            final ResourceNotFoundException ex) {
        String message = ex.getMessage();
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        ApiErrorResponse response = new ApiErrorResponse(statusCode, message);
        return new ResponseEntity<ApiErrorResponse>(response,
                HttpStatus.NOT_FOUND);
    }

    /**
     * Handles DuplicateResourceException and returns an appropriate response.
     *
     * @param ex The DuplicateResourceException to be handled.
     * @return A ResponseEntity containing the error response for
     *         DuplicateResourceException.
     */
    @ExceptionHandler(DuplicateResourceException.class)
    public final ResponseEntity<ApiErrorResponse>
                      duplicateResourceExceptionHandler(
            final DuplicateResourceException ex) {
        String message = ex.getMessage();
        HttpStatus statusCode = HttpStatus.FOUND;
        ApiErrorResponse response = new ApiErrorResponse(statusCode, message);

        return new ResponseEntity<ApiErrorResponse>(response, HttpStatus.FOUND);

    }

    /**
     * Handles BadCredentialsException and returns an appropriate response.
     *
     * @param ex The BadCredentialsException to be handled.
     * @return A ResponseEntity containing the error response for
     *         BadCredentialsException.
     */

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ApiErrorResponse> badCredentialExceptionHandler(
            final BadCredentialsException ex) {
        String message = ex.getMessage();
        HttpStatus statusCode = HttpStatus.UNAUTHORIZED;
        ApiErrorResponse response = new ApiErrorResponse(statusCode, message);
        return new ResponseEntity<ApiErrorResponse>(response,
                HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles MethodArgumentNotValidException and returns an appropriate
     * response.
     *
     * @param ex The MethodArgumentNotValidException to be handled.
     * @return A ResponseEntity containing the error response for
     *         MethodArgumentNotValidException.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, String>>
                   methodArgumentNotValidExceptionHandler(
            final MethodArgumentNotValidException ex) {
        Map<String, String> response = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            response.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(response,
                HttpStatus.BAD_REQUEST);
    }

}
