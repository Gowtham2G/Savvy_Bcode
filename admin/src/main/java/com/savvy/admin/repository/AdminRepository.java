package com.savvy.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savvy.admin.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
	

}
