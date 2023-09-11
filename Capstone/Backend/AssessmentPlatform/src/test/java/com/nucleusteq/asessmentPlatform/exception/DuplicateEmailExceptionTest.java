package com.nucleusteq.asessmentPlatform.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DuplicateEmailExceptionTest {

    @Test
    void testDuplicateEmailException() {
        String message = "Wrong Password";
        DuplicateEmailException exception = new DuplicateEmailException(
                message);
        assertEquals(message, exception.getMessage());
    }

}
