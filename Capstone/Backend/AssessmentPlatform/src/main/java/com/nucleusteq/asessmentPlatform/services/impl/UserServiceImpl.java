package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.nucleusteq.asessmentPlatform.dto.UserDto;
import com.nucleusteq.asessmentPlatform.entities.LoginRequest;
import com.nucleusteq.asessmentPlatform.entities.User;
import com.nucleusteq.asessmentPlatform.exception.BadCredentialsException;
import com.nucleusteq.asessmentPlatform.exception.DuplicateResourceException;
import com.nucleusteq.asessmentPlatform.exception.ResourceNotFoundException;
import com.nucleusteq.asessmentPlatform.exception.UserNotFoundException;
import com.nucleusteq.asessmentPlatform.repositories.UserRepo;
import com.nucleusteq.asessmentPlatform.service.UserService;

/**
 * Implementation of the {@link UserService} interface for managing user-related
 * operations.
 *  It uses a logger to log messages related to its functionality.
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * This is User Repository object that is for calling. the repository
     * methods.
     */
    @Autowired
    private UserRepo userRepo;

    /**
     * This is password encoder object it is for encryption of password field.
     *
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * This is ModelMapper object to map the object.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * The logger instance for logging messages related to UserServiceImpl.
     */
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public final String registerUser(final UserDto userDto) {
        User user = this.dtoToUser(userDto);
        Optional<User> existingUserByEmail = userRepo
                .findByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            logger.error("Email address already exists");
            throw new DuplicateResourceException(
                    "Email address already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("user");
        userRepo.save(user);
        logger.info("User Register successfully");
        return "User Register successfully";
    }

    @Override
    public final List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream()
                .map(user -> this.userToDto(user)).collect(Collectors.toList());
        logger.info("Get All Users");
        return userDtos;
    }

    @Override
    public final String deleteUser(final int id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "user not found with id " + id));
        userRepo.delete(user);
        logger.info("User deleted successfully");
        return id + " deleted successfully";
    }

    @Override
    public final Map<String, String> loginUser(
            final LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(),
                user.getPassword())) {
            logger.error("Bad Credentials");
            throw new BadCredentialsException("Invalid password");
        }
        Map<String, String> response = new HashMap<>();
        response.put("Status", "True");
        response.put("Role", user.getRole());
        response.put("Name", user.getFirstName());
        logger.info("login successfully");
        return response;
    }

    /**
     * Converts a {@link User} entity to a {@link UserDto} object.
     *
     * @param user The User entity to be converted.
     * @return The converted UserDto object.
     */
    public final UserDto userToDto(final User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    /**
     * Converts a {@link UserDto} object to a {@link User} entity.
     *
     * @param userDto The UserDto object to be converted.
     * @return The converted User entity.
     */

    public final User dtoToUser(final UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

}
