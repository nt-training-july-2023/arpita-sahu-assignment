package com.nucleusteq.asessmentPlatform.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nucleusteq.asessmentPlatform.dto.ApiResponse;

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
    public final ResponseEntity<ApiResponse>
                   resourceNotFoundExceptionHandler(
            final ResourceNotFoundException ex) {
        String message = ex.getMessage();
        Integer statusCode = HttpStatus.NOT_FOUND.value();
        ApiResponse response = new ApiResponse(message, statusCode);
        return new ResponseEntity<ApiResponse>(response,
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
    public final ResponseEntity<ApiResponse>
                      duplicateResourceExceptionHandler(
            final DuplicateResourceException ex) {
        String message = ex.getMessage();
        Integer statusCode = HttpStatus.FOUND.value();
        ApiResponse response = new ApiResponse(message, statusCode);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.FOUND);

    }

    /**
     * Handles BadCredentialsException and returns an appropriate response.
     * @param ex The BadCredentialsException to be handled.
     * @return A ResponseEntity containing the error response for
     *         BadCredentialsException.
     */

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ApiResponse> badCredentialExceptionHandler(
            final BadCredentialsException ex) {
        String message = ex.getMessage();
        Integer statusCode = HttpStatus.UNAUTHORIZED.value();
        ApiResponse response = new ApiResponse(message, statusCode);
        return new ResponseEntity<ApiResponse>(response,
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
            response.put("statusCode", "400");
        });
        return new ResponseEntity<Map<String, String>>(response,
                HttpStatus.BAD_REQUEST);
    }

}
