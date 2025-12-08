package com.savvy.cart.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savvy.cart.config.JwtUtilConfig;
import com.savvy.cart.dto.CartRequestDto;
import com.savvy.cart.dto.CartResponseDto;
import com.savvy.cart.service.CartService;
import com.savvy.cart.service.CartServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartServiceImpl cartServiceImpl;
    private final JwtUtilConfig jwtUtil;
    
    @PostMapping("/add")
    public CartResponseDto addToCart(@RequestHeader("Authorization") String token,
                                     @RequestBody CartRequestDto dto) {
        String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;

        Long userId = jwtUtil.extractUserId(jwt);

        if (userId == null) {
            throw new RuntimeException("JWT token does not contain userId or token is invalid");
        }

        return cartServiceImpl.addToCart(userId, dto);
    }


    
    @DeleteMapping("/remove/{id}")
    public String removeFromCart(@RequestHeader("Authorization") String token, 
                                 @PathVariable Long id) {
        cartServiceImpl.removeFromCart(id);
        return "Item removed successfully";
    }
    
    @GetMapping("/mycart")
    public List<CartResponseDto> getCart(@RequestHeader("Authorization") String token) {
    	String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;
        Long userId = jwtUtil.extractUserId(jwt); // ✅ Extract userId from token
        return cartServiceImpl.getCartByUser(userId);
    }

}
