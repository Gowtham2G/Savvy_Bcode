package com.savvy.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savvy.order.dto.OrderRequestDto;
import com.savvy.order.dto.OrderResponseDto;
import com.savvy.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class Ordercontroller {

    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequest) {
        try {
            // ✅ Defensive null checks before passing to service
            if (orderRequest == null) {
                return ResponseEntity.badRequest().body("Request body cannot be null");
            }

            if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
                return ResponseEntity.badRequest().body("Order must contain at least one item");
            }

            // ✅ Call service to process the order
            OrderResponseDto response = orderService.placeOrder(orderRequest);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // ✅ Catch unexpected exceptions gracefully
            return ResponseEntity.internalServerError().body("Error placing order: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUser(@PathVariable Long userId) {
        List<OrderResponseDto> responses = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(responses);
    }
    
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderResponseDto>> getOrderById(@PathVariable Long orderId) {
        List<OrderResponseDto> response = orderService.getOrderById(orderId);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {
        OrderResponseDto updated = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order with ID " + orderId + " cancelled successfully.");
    }
    
}
