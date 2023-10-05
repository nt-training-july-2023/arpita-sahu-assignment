package com.nucleusteq.asessmentPlatform.exception;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.nucleusteq.asessmentPlatform.dto.ApiResponse;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testResourceNotFoundExceptionHandler() {
        ResourceNotFoundException exception = new ResourceNotFoundException(
                "Resource not found");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler
                .resourceNotFoundExceptionHandler(exception);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ApiResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.NOT_FOUND.value(), errorResponse.getStatus());
        assertEquals("Resource not found", errorResponse.getMessage());
    }

    @Test
    public void testDuplicateResourceExceptionHandler() {
        DuplicateResourceException exception = new DuplicateResourceException(
                "Duplicate Resource");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler
                .duplicateResourceExceptionHandler(exception);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        ApiResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.FOUND.value(), errorResponse.getStatus());
        assertEquals("Duplicate Resource", errorResponse.getMessage());
    }

    @Test
    public void testBadCredentialExceptionHandler() {
        BadCredentialsException exception = new BadCredentialsException(
                "Bad Credentials");
        ResponseEntity<ApiResponse> responseEntity = globalExceptionHandler
                .badCredentialExceptionHandler(exception);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        ApiResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED.value(),
                errorResponse.getStatus());
        assertEquals("Bad Credentials", errorResponse.getMessage());
    }

    @Test
    public void testMethodArgumentNotValidExceptionHandler() {
        GlobalExceptionHandler globalException = new GlobalExceptionHandler();
        MethodArgumentNotValidException ex = mock(
                MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("fieldName", "fieldName",
                "message");
        when(bindingResult.getAllErrors())
                .thenReturn(Collections.singletonList(fieldError));
        when(ex.getBindingResult()).thenReturn(bindingResult);
        ResponseEntity<Map<String, String>> responseEntity = globalException
                .methodArgumentNotValidExceptionHandler(ex);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("message", responseEntity.getBody().get("fieldName"));
    }

}
