package com.nucleusteq.asessmentPlatform.service;

import com.nucleusteq.asessmentPlatform.dto.UserDto;
import com.nucleusteq.asessmentPlatform.entities.LoginRequest;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String registerUser(UserDto user);

    List<UserDto> getAllUsers();

    Map<String, String> loginUser(LoginRequest loginRequest);

    String deleteUser(int id);
}
