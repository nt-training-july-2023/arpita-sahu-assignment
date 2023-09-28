package com.nucleusteq.asessmentPlatform.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nucleusteq.asessmentPlatform.dto.UserDto;
import com.nucleusteq.asessmentPlatform.entities.LoginRequest;
import com.nucleusteq.asessmentPlatform.service.UserService;

import jakarta.validation.Valid;

/**
 * Controller class that handles user-related operations.
 */
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    /**
     * The service responsible for managing user-related operations.
     */
    @Autowired
    private UserService userService;
    /**
     * The logger instance for logging messages related to UserController.
     */
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Endpoint for registering a new user.
     *
     * @param userDto The UserDto object containing user registration details.
     * @return A message indicating the result of the registration operation.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public final String saveUser(@RequestBody @Valid final UserDto userDto) {
        logger.info("User Register Successfully");
        return userService.registerUser(userDto);
    }

    /**
     * Endpoint for retrieving a list of all users.
     *
     * @return A list of UserDto objects representing all users.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final List<UserDto> getAllUsers() {
        logger.info("Get All Users");
        return userService.getAllUsers();
    }

    /**
     * Endpoint for deleting a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return A message indicating the result of the deletion operation.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final String deleteUser(@PathVariable final int id) {
        logger.info("User deleted Successfully");
        return userService.deleteUser(id);
    }

    /**
     * Endpoint for user login.
     *
     * @param loginRequest The LoginRequest object containing user login
     *                     credentials.
     * @return A map containing the login response, including authentication
     *         status and token.
     */
    @PostMapping("/login")
    public final Map<String, String> loginUser(
            @RequestBody @Valid final LoginRequest loginRequest) {
        logger.info("login Successfully");
        return userService.loginUser(loginRequest);
    }
}
