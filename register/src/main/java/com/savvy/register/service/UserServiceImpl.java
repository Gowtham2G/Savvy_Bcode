package com.savvy.register.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.registration.dto.RegisterRequestDTO;
import com.example.registration.dto.RegisterResponseDTO;
import com.savvy.register.entity.UserRegistration;
import com.savvy.register.exception.EmailAlreadyExistsException;
import com.savvy.register.repository.UserRepository;

@Service
public class UserServiceImpl implements UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterResponseDTO registerUser(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        UserRegistration user = new UserRegistration();
        user.setUserName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress()); 
        user.setRole("USER");
        user.setStatus("ACTIVE");

        userRepository.save(user);

        return new RegisterResponseDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.getRole(),
                user.getStatus()
        );
    }

    @Override
    public RegisterResponseDTO getUserById(Long id) {
        UserRegistration user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return new RegisterResponseDTO(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getRole(),
                user.getRole(),
                user.getStatus(), null
        );
    }

    @Override
    public boolean isEmailAvailable(String email) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered");
    }
}
