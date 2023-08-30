package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nucleusteq.asessmentPlatform.dto.UserDto;
import com.nucleusteq.asessmentPlatform.entities.LoginRequest;
import com.nucleusteq.asessmentPlatform.entities.User;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.exception.DuplicateEmailException;
import com.nucleusteq.asessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.UserRepo;
import com.nucleusteq.asessmentPlatform.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String registerUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        Optional<User> existingUserByEmail = userRepo
                .findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new DuplicateEmailException("Email address already exists");
        }
        // domain email
        // duplicate mobile no.
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setRole("user");
        userRepo.save(newUser);
        return newUser.getUserId() + " Register successfully";
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> this.userToDto(user))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public String deleteUser(int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "user not found with id " + id));
        userRepo.delete(user);
        return id + " deleted sucessfully";
    }

    @Override
    public Map<String, String> loginUser(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(),
                user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        Map<String, String> response = new HashMap<>();
        response.put("Status", "True");
        response.put("Role", user.getRole());
        return response;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User dtoToUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

}
