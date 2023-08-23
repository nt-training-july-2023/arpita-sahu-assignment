package com.nucleusteq.asessmentPlatform.service;

import com.nucleusteq.asessmentPlatform.entities.LoginRequest;
import com.nucleusteq.asessmentPlatform.entities.User;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	User registerUser(User user);
	List<User> getAllUsers();
	User loginUser(LoginRequest loginRequest) ;
}