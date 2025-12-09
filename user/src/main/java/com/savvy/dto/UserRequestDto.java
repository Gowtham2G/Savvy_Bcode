package com.savvy.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String email;
	private String password;
    private String role;
    
    
    public UserRequestDto(String name, String email, String password, String role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
}
