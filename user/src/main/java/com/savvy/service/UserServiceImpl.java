package com.savvy.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.savvy.dto.UserRequestDto;
import com.savvy.dto.UserResponseDto;
import com.savvy.entity.User;
import com.savvy.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRequestDto dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setStatus("ACTIVE");

        User savedUser = userRepository.save(user);

        return UserResponseDto.builder()
                .userId(savedUser.getUserId())   // ✅ WILL WORK NOW
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .status(savedUser.getStatus())   // ✅ WILL WORK NOW
                .build();
    }
}

