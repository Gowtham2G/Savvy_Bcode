package com.savvy.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDto {

	private String paymentMethod;
    private double amount;
    private String transactionId;
    private String paymentStatus; 
}
