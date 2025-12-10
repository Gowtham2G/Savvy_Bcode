package com.savvy.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.savvy.admin.dto.AdminRequestDto;
import com.savvy.admin.dto.AdminResponseDto;
import com.savvy.admin.entity.SystemAdmin;
import com.savvy.admin.exception.UserAlreadyExistsException;
import com.savvy.admin.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto requestDto) {

        // ✅ 1. HANDLE DUPLICATES GRACEFULLY
    	if (adminRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("This email is already registered");
        }
        // ✅ 2. BUILD THE ADMIN (Using the Unified Table structure)
        SystemAdmin admin = new SystemAdmin();
        admin.setUsername(requestDto.getUsername());
        admin.setEmail(requestDto.getEmail());
        admin.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        admin.setRole("ADMIN");   // Force role to ADMIN
        admin.setStatus("ACTIVE"); // Set status so they can login

        // ✅ 3. SAVE (ID is generated here)
        SystemAdmin savedAdmin = adminRepository.save(admin);

        // ✅ 4. RETURN RESPONSE (ID will now be correct, not 0)
        return new AdminResponseDto(
                savedAdmin.getId(),
                savedAdmin.getUsername(),
                savedAdmin.getEmail(),
                savedAdmin.getRole()
        );
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(admin -> new AdminResponseDto(
                        admin.getId(),
                        admin.getUsername(),
                        admin.getEmail(),
                        admin.getRole()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDto getAdminById(Long id) {
        SystemAdmin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));

        return new AdminResponseDto(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getRole()
        );
    }
}