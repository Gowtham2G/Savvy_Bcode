package com.savvy.order.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.savvy.order.entity.OrderStatus;

public class OrderResponseDto {

    private long id;
    private Long userId;
    
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderItemResponseDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemResponseDto> items) {
		this.items = items;
	}

	public PaymentResponseDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentResponseDto payment) {
		this.payment = payment;
	}

	public ShippingAddressResponseDto getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddressResponseDto shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	private OrderStatus status;
    private double totalAmount;
    private LocalDateTime orderDate;
    private List<OrderItemResponseDto> items;
    private PaymentResponseDto payment;
    private ShippingAddressResponseDto shippingAddress;

    public OrderResponseDto(long id, Long userId, OrderStatus status, double totalAmount,
                            LocalDateTime orderDate, List<OrderItemResponseDto> items,
                            PaymentResponseDto payment, ShippingAddressResponseDto shippingAddress) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.items = items;
        this.payment = payment;
        this.shippingAddress = shippingAddress;
    }

    // getters and setters
}

