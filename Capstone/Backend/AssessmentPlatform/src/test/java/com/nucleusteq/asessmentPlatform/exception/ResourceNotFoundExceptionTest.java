package com.nucleusteq.asessmentPlatform.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResourceNotFoundExceptionTest {

    @Test
    void testResourceNotFoundException() {
        String message = "Wrong Password";
        ResourceNotFoundException exception = new ResourceNotFoundException(
                message);
        assertEquals(message, exception.getMessage());
    }
}
