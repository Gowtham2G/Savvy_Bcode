package com.savvy.cart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.savvy.cart.dto.CartRequestDto;
import com.savvy.cart.dto.CartResponseDto;
import com.savvy.cart.entity.CartItem;
import com.savvy.cart.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
	private final CartRepository cartRepository;

	@Override
	public CartResponseDto addToCart(Long userId, CartRequestDto dto) {
	    // userId is guaranteed non-null here
	    CartItem item = new CartItem(userId, dto.getProductId(), dto.getQuantity());
	    cartRepository.save(item);
	    return new CartResponseDto(item.getId(), userId, item.getProductId(), item.getQuantity());
	}



	@Override
	public List getCartByUser(Long userId) {
	    return cartRepository.findByUserId(userId)
                .stream()
                .map(i -> new CartResponseDto(i.getId(), i.getUserId(), i.getProductId(), i.getQuantity()))
                .collect(Collectors.toList());
	}

	@Override
	public void removeFromCart(Long Id) {
		cartRepository.deleteById(Id);		
	}
	
	

}
