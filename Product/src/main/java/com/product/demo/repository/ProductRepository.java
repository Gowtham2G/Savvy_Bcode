package com.product.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
    List<Product> findByCategory(String category);


}
