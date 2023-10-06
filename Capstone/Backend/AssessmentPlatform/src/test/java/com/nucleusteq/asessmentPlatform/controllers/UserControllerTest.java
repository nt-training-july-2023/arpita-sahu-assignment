package com.nucleusteq.asessmentPlatform.controllers;

import com.nucleusteq.asessmentPlatform.dto.ApiResponse;
import com.nucleusteq.asessmentPlatform.dto.UserDto;
import com.nucleusteq.asessmentPlatform.entities.LoginRequest;
import com.nucleusteq.asessmentPlatform.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setFirstName("Arpita");
        userDto.setLastName("Sahu");
        userDto.setEmail("arpita@nucleusteq.com");
        userDto.setPassword("1234");
        userDto.setPhoneNumber("9767276262");

        when(userService.registerUser(userDto))
                .thenReturn("1 Register successfully");
        ApiResponse response = userController.saveUser(userDto);
        assertEquals("User Register successfully", response.getMessage());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testGetAllUsers() {
        UserDto user1 = new UserDto();
        user1.setUserId(1);
        user1.setFirstName("Arpita");
        user1.setLastName("Sahu");
        user1.setEmail("arpita@nucleusteq.com");

        UserDto user2 = new UserDto();
        user2.setUserId(1);
        user2.setFirstName("Arpita");
        user2.setLastName("Sahu");
        user2.setEmail("arpita@nucleusteq.com");

        List<UserDto> userList = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(userList);
        List<UserDto> response = userController.getAllUsers();
        assertEquals(userList, response);
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;
        when(userService.deleteUser(userId))
                .thenReturn("1 deleted successfully");
        ApiResponse response = userController.deleteUser(userId);
        assertEquals("User deleted successfully", response.getMessage());
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void testLoginUser() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("arpita@nucleusteq.com");
        loginRequest.setPassword("1234");
        Map<String, String> loginResponse = Map.of("Status", "True", "Role",
                "user");
        when(userService.loginUser(loginRequest)).thenReturn(loginResponse);
        Map<String, String> response = userController.loginUser(loginRequest);
        assertEquals(loginResponse, response);
    }
}
