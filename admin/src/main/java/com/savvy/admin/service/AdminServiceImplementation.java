package com.savvy.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.savvy.admin.dto.AdminRequestDto;
import com.savvy.admin.dto.AdminResponseDto;
import com.savvy.admin.entity.Admin;
import com.savvy.admin.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto requestDto) {

        Admin admin = new Admin();
        admin.setUsername(requestDto.getUsername());
        admin.setEmail(requestDto.getEmail());
        admin.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        admin.setRole("ADMIN");

        adminRepository.save(admin);

        return new AdminResponseDto(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getRole()
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
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));

        return new AdminResponseDto(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getRole()
        );
    }
}
