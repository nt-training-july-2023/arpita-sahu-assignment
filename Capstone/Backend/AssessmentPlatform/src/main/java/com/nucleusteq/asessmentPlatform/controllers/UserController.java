package com.nucleusteq.asessmentPlatform.controllers;

import java.util.List;
import java.util.Map;

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

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PostMapping("/login")
    public Map<String, String> loginUser(
            @RequestBody LoginRequest loginRequest) {
        Map<String, String> responseMap = userService.loginUser(loginRequest);
//        try {
//            User authenticatedUser = userService.loginUser(loginRequest);
//            return ResponseEntity.ok(authenticatedUser);
//        } catch (UserNotFoundException | BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
        return responseMap;
    }
}
