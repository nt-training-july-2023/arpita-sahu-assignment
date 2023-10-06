package com.nucleusteq.asessmentPlatform.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class ApiResponseTest {
    
    ApiResponse apiResponse;
    
    @BeforeEach
    void init() {
        apiResponse = new ApiResponse();
    }

    @Test
    void testGettersAndSetters() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Not Found");
        apiResponse.setStatus(404);
        assertEquals(404, apiResponse.getStatus());
        assertEquals("Not Found", apiResponse.getMessage());
        
    }
    @Test
    void testParameterizedConstructor() {
        ApiResponse apiResponse = new ApiResponse("OK",200);
        assertEquals(200, apiResponse.getStatus());
        assertEquals("OK", apiResponse.getMessage());
    }
}
