package com.nucleusteq.asessmentPlatform.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryNotFoundExceptionTest {

	@Test
    void testCategoryNotFoundException() {
        String message = "Wrong Password";
        CategoryNotFoundException exception = new CategoryNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}
