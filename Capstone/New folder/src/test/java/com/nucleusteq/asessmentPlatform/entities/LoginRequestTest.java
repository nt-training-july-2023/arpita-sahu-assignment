package com.nucleusteq.asessmentPlatform.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginRequestTest {

    LoginRequest loginRequest;

    @BeforeEach
    void init() {
        loginRequest = new LoginRequest();
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(null, loginRequest.getEmail());
        assertEquals(null, loginRequest.getPassword());
        
        loginRequest.setEmail("arpita@nucleusteq.com");
        loginRequest.setPassword("1234");
        
        assertEquals("arpita@nucleusteq.com", loginRequest.getEmail());
        assertEquals("1234", loginRequest.getPassword());
    }

    @Test
    void testDefaultConstructor() {
        LoginRequest loginRequest = new LoginRequest();
        assertEquals(null, loginRequest.getEmail());
        assertEquals(null, loginRequest.getPassword());      
    }

    @Test
    void testParameterisedConstructor() {
        LoginRequest loginRequest = new LoginRequest("arpita@nucleusteq.com","1234");
        assertEquals("arpita@nucleusteq.com", loginRequest.getEmail());
        assertEquals("1234", loginRequest.getPassword());
    }

}
