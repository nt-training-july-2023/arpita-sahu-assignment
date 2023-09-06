package com.nucleusteq.asessmentPlatform.services.impl;

import com.nucleusteq.asessmentPlatform.dto.UserDto;
import com.nucleusteq.asessmentPlatform.entities.LoginRequest;
import com.nucleusteq.asessmentPlatform.entities.User;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private ModelMapper modelMapper;

 
	@SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId(1);
        userDto.setFirstName("Arpita");
        userDto.setLastName("Sahu");
        userDto.setEmail("apita@nucleusteq.com");
        userDto.setPassword("1234");
        userDto.setPhoneNumber("4674783797");
        userDto.setRole("user");

        User newUser = new User();
        newUser.setUserId(userDto.getUserId());
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        newUser.setPhoneNumber(userDto.getPhoneNumber());
        newUser.setRole(userDto.getRole());
       
        when(userRepo.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(userRepo.save(any(User.class))).thenReturn(newUser);
        
     }

    @Test
    public void testRegisterUserWithDuplicateEmail() {
        UserDto userDto = new UserDto();
        userDto.setEmail("arpita@nucleusteq.com");
        when(userRepo.findByEmail(userDto.getEmail())).thenReturn(Optional.of(new User()));
        assertThrows(NullPointerException.class, () -> userService.registerUser(userDto));
    }
    


    @Test
    public void testLoginUser() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("arpita@nucleusteq.com");
        loginRequest.setPassword("1234");

        User user = new User();
        user.setPassword("encodedPassword");
        user.setRole("user");

        when(userRepo.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);

        Map<String, String> response = userService.loginUser(loginRequest);

        assertNotNull(response);
        assertEquals("True", response.get("Status"));
        assertEquals("user", response.get("Role"));
    }

    @Test
    public void testLoginUserWithInvalidPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("arpita@nucleusteq.com");
        loginRequest.setPassword("wrongPassword");

        User user = new User();
        user.setPassword("encodedPassword");
        when(userRepo.findByEmail(loginRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(false);
        assertThrows(BadCredentialsException.class, () -> userService.loginUser(loginRequest));
    }
}
