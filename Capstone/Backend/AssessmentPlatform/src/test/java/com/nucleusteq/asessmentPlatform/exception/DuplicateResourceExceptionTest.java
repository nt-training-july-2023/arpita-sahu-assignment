package com.nucleusteq.asessmentPlatform.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DuplicateResourceExceptionTest {

	 @Test
	    void testDuplicateResourceException() {
	        String message = "Wrong Password";
	        DuplicateResourceException exception = new DuplicateResourceException(message);
	        assertEquals(message, exception.getMessage());
	    }

}
