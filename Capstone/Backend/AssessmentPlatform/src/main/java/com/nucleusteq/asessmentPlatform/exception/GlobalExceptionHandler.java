package com.nucleusteq.asessmentPlatform.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ApiErrorResponse> resourceNotFoundExceptionHandler(
            final ResourceNotFoundException ex) {
        String message = ex.getMessage();
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        ApiErrorResponse response = new ApiErrorResponse(statusCode, message);
        return new ResponseEntity<ApiErrorResponse>(response,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public final ResponseEntity<ApiErrorResponse> DuplicateResourceExceptionHandler(
            final DuplicateResourceException ex) {
        String message = ex.getMessage();
        HttpStatus statusCode = HttpStatus.FOUND;
        ApiErrorResponse response = new ApiErrorResponse(statusCode, message);

        return new ResponseEntity<ApiErrorResponse>(response, HttpStatus.FOUND);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ApiErrorResponse> BadCredentialExceptionHandler(
            final BadCredentialsException ex) {
        String message = ex.getMessage();
        HttpStatus statusCode = HttpStatus.UNAUTHORIZED;
        ApiErrorResponse response = new ApiErrorResponse(statusCode, message);
        return new ResponseEntity<ApiErrorResponse>(response,
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(
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
