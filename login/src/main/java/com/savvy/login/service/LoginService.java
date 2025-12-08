package com.savvy.login.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.savvy.login.config.JwtUtil; // Updated to match your cart-style JWT
import com.savvy.login.entity.User;
import com.savvy.login.exception.InvalidCredentialsException;
import com.savvy.login.repository.UserRepository;
import com.savvy.login.dto.LoginRequestDto;
import com.savvy.login.dto.LoginResponseDto;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // Use the config class that generates JWTs

    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    
    public LoginResponseDto login(LoginRequestDto request) {
        // 1️⃣ Fetch user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // 2️⃣ Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // 3️⃣ Generate JWT including userId and role in claims
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getUserId());

        // 4️⃣ Return token and user info
        return new LoginResponseDto(token, user.getRole(), "Login successful");
    }
}
