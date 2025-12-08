package com.savvy.register.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserRegistration {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long userId;
		
		private String userName;
		
		@Column(unique=true)
		private String email;
		private String password;
		private String phone;
		private String address;
		private String role;
		private String status;
	

}
