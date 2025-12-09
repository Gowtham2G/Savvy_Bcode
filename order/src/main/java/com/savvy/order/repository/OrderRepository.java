package com.savvy.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savvy.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {
	
	List<Order> findByUserId(Long userId);
	List<Order> findByStatus(String status);
}
