package com.savvy.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savvy.admin.entity.SystemAdmin;

public interface AdminRepository extends JpaRepository<SystemAdmin,Long>{

	Optional<SystemAdmin> findByEmail(String email);	

}
