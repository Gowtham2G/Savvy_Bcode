package com.savvy.admin.service;

import java.util.List;

import com.savvy.admin.dto.AdminRequestDto;
import com.savvy.admin.dto.AdminResponseDto;

public interface AdminService {

	AdminResponseDto createAdmin(AdminRequestDto responseDTO);
	List<AdminResponseDto> getAllAdmins();
	AdminResponseDto getAdminById(Long id);
}
