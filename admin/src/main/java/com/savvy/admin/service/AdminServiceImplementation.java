package com.savvy.admin.service;

import com.savvy.admin.dto.AdminRequestDto;
import com.savvy.admin.dto.AdminResponseDto;
import com.savvy.admin.entity.Admin;  // ✅ Use your entity here
import com.savvy.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder; // ✅ Add this import
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto requestDto) {
        // ✅ Use your own Admin entity (not Kafka Admin)
        Admin admin = new Admin();
        admin.setUsername(requestDto.getUsername());
        admin.setEmail(requestDto.getEmail());
        admin.setPassword(passwordEncoder.encode(requestDto.getPassword())); // 🔑 hashed password
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
        Admin admin = adminRepository.findById(id) // ✅ Capitalized Admin
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        return new AdminResponseDto(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getRole()
        );
    }
}
