package com.nucleusteq.asessmentPlatform.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	@Override
	public User registerUser(User user) {
		Optional<User> existingUserByEmail = userRepo.findByEmail(user.getEmail());
		 if (existingUserByEmail.isPresent()) {
			 throw new DuplicateEmailException("Email address already exists");
		 }
	    User newUser = new User();
	    newUser.setFirstName(user.getFirstName());
	    newUser.setLastName(user.getLastName());
	    newUser.setEmail(user.getEmail());
	    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
	    newUser.setPhoneNumber(user.getPhoneNumber());
	    newUser.setRole("user"); 
	    return userRepo.save(newUser);
	}
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
    public User loginUser(LoginRequest loginRequest) {
        User user = userRepo.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        System.out.print(loginRequest.getPassword());
        boolean isright = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        System.out.print(isright);
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        	
            throw new BadCredentialsException("Invalid password");
        }
        
        return user;
    }
}


