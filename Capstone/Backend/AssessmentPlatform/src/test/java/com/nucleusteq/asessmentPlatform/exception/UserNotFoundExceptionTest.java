package com.nucleusteq.asessmentPlatform.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserNotFoundExceptionTest {

    @Test
    void testUserNotFoundException() {
        String message = "Wrong Password";
        UserNotFoundException exception = new UserNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

}
