package com.savvy.cart.service;

import java.util.List;

import com.savvy.cart.dto.CartRequestDto;
import com.savvy.cart.dto.CartResponseDto;

public interface CartService {

	CartResponseDto addToCart(Long userId, CartRequestDto dto);
	
	List<CartResponseDto> getCartByUser(Long userId);
	
	void removeFromCart(Long Id);
}
