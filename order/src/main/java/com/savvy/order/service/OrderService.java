package com.savvy.order.service;

import java.util.List;

import com.savvy.order.dto.OrderRequestDto;
import com.savvy.order.dto.OrderResponseDto;

public interface OrderService {

	OrderResponseDto placeOrder(OrderRequestDto dto);
	
	List <OrderResponseDto> getOrdersByUser(Long userId);

	List <OrderResponseDto> getOrderById(Long orderId);
	
	OrderResponseDto updateOrderStatus(Long orderId, String Status);
	
	void cancelOrder(Long orderId);
	
	
}
