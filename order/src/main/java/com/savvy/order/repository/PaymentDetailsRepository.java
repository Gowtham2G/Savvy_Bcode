package com.savvy.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.savvy.order.entity.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long> {


    PaymentDetails findByOrderId(Long orderId);
}
