package com.nucleusteq.asessmentPlatform.services.impl;

import com.nucleusteq.asessmentPlatform.dto.UserDto;
import com.nucleusteq.asessmentPlatform.entities.LoginRequest;
import com.nucleusteq.asessmentPlatform.entities.User;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.exception.UserNotFoundException;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId(0);
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

        when(userRepo.findByEmail(newUser.getEmail()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(userDto.getPassword()))
                .thenReturn("encodedPassword");
        when(modelMapper.map(userDto, User.class)).thenReturn(newUser);
        when(userRepo.save(any(User.class))).thenReturn(newUser);

        String result = userService.registerUser(userDto);
        assertEquals(userDto.getUserId() + " Register successfully", result);

    }

    @Test
    public void testRegisterUserWithDuplicateEmail() {
        UserDto userDto = new UserDto();
        userDto.setEmail("arpita@nucleusteq.com");
        when(userRepo.findByEmail(userDto.getEmail()))
                .thenReturn(Optional.of(new User()));
        assertThrows(NullPointerException.class,
                () -> userService.registerUser(userDto));
    }

    @Test
    public void testLoginUser() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("arpita@nucleusteq.com");
        loginRequest.setPassword("1234");

        User user = new User();
        user.setPassword("encodedPassword");
        user.setRole("user");

        when(userRepo.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginRequest.getPassword(),
                user.getPassword())).thenReturn(true);

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
        when(userRepo.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginRequest.getPassword(),
                user.getPassword())).thenReturn(false);
        assertThrows(BadCredentialsException.class,
                () -> userService.loginUser(loginRequest));
    }

    @Test
    public void testGetAllUsers() {
        List<User> addUsers = new ArrayList<>();
        addUsers.add(new User(1, "Arpita", "Sahu", "arpita@gmail.com", "1234",
                "7389372823", "admin"));
        addUsers.add(new User(2, "Omi", "Sinha", "omi@gmail.com", "1234",
                "73679372823", "user"));

        when(userRepo.findAll()).thenReturn(addUsers);

        List<UserDto> userDtos = userService.getAllUsers();
        assertNotNull(userDtos);
        assertEquals(addUsers.size(), userDtos.size());

    }

    @Test
    void testDeleteUser() {
        int userIdToDelete = 2;
        User userToDelete = new User(userIdToDelete, "Omi", "Sinha",
                "omi@gmail.com", "1234", "73679372823", "user");

        when(userRepo.findById(userIdToDelete))
                .thenReturn(Optional.of(userToDelete));
        String result = userService.deleteUser(userIdToDelete);
        assertEquals(userIdToDelete + " deleted successfully", result);
    }

    @Test
    void testDeleteUserNotFound() {
        int userIdToDelete = 1;
        when(userRepo.findById(userIdToDelete)).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> userService.deleteUser(userIdToDelete));

        assertEquals("user not found with id " + userIdToDelete,
                exception.getMessage());
    }

}
