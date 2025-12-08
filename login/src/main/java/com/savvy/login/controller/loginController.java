package com.savvy.login.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savvy.login.dto.LoginRequestDto;
import com.savvy.login.dto.LoginResponseDto;
import com.savvy.login.service.LoginService;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class loginController {

	  private final LoginService loginService;

	public loginController(LoginService loginService) {
		super();
		this.loginService = loginService;
	}
	
	 @PostMapping("/login")
	 public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
	        return ResponseEntity.ok(loginService.login(request));
	    } 
}
