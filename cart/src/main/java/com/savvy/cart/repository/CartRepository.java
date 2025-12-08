package com.savvy.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savvy.cart.entity.CartItem;

public interface CartRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUserId(Long userId);

}
