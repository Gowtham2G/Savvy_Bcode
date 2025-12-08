 package com.savvy.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

	private Long userId; // ID of the user placing the order

    private List<OrderItemRequestDto> items; // List of products in cart

    private PaymentRequestDto payment; // Payment info (mode, amount, etc.)

    private ShippingAddressRequestDto shippingAddress; 
}
