package com.savvy.admin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savvy.admin.dto.AdminRequestDto;
import com.savvy.admin.dto.AdminResponseDto;
import com.savvy.admin.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

	private final AdminService adminService ;
	
	@PostMapping("/add")
    public AdminResponseDto createAdmin(@RequestBody AdminRequestDto requestDto) {
		return adminService.createAdmin(requestDto);
    }

	@GetMapping("/all")
	 public List<AdminResponseDto> getAllAdmins(){
		 return adminService.getAllAdmins();
	 }
	
	@GetMapping("/{id}")
    public AdminResponseDto getAdminById(@PathVariable Long id) {
    	return adminService.getAdminById(id);
    }
    
}

