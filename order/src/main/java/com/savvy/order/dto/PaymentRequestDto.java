package com.savvy.order.dto;

public class PaymentRequestDto {

	private String paymentMethod; // e.g., CARD, UPI, COD
    private double amount;        // total amount of the order
    private String transactionId;
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}  
}
