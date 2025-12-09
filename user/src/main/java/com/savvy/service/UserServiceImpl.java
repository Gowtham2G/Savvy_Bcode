package com.savvy.service;

import com.savvy.dto.UserRequestDto;
import com.savvy.dto.UserResponseDto;
import com.savvy.entity.User;
import com.savvy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
 
	@Override
	public UserResponseDto getUserProfile(String email) {
        // 1. Find the user in the DB using the email passed in
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found for email: " + email));

        // 2. Convert Entity to DTO and return
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
	}
}