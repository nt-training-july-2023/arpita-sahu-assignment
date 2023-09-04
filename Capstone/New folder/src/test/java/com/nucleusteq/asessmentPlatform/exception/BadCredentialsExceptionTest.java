package com.nucleusteq.asessmentPlatform.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BadCredentialsExceptionTest {

	@Test
    void testBadCredentialsException() {
        String message = "Wrong Password";
        BadCredentialsException exception = new BadCredentialsException(message);
        assertEquals(message, exception.getMessage());
    }

}
