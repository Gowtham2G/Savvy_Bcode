package com.savvy.order.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.savvy.order.dto.OrderItemResponseDto;
import com.savvy.order.dto.OrderRequestDto;
import com.savvy.order.dto.OrderResponseDto;
import com.savvy.order.dto.PaymentResponseDto;
import com.savvy.order.dto.ShippingAddressResponseDto;
import com.savvy.order.entity.Order;
import com.savvy.order.entity.OrderItem;
import com.savvy.order.entity.OrderStatus;
import com.savvy.order.entity.PaymentDetails;
import com.savvy.order.entity.ShippingAddress;
import com.savvy.order.repository.OrderItemRepository;
import com.savvy.order.repository.OrderRepository;
import com.savvy.order.repository.PaymentDetailsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentDetailsRepository paymentRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto dto) {
        // Save Shipping Address
        ShippingAddress address = new ShippingAddress();
        address.setName(dto.getShippingAddress().getFullName());
        address.setStreet(dto.getShippingAddress().getAddressLine());
        address.setCity(dto.getShippingAddress().getCity());
        address.setState(dto.getShippingAddress().getState());
        address.setPostalCode(dto.getShippingAddress().getPostalCode());
        address.setCountry(dto.getShippingAddress().getCountry());
        address.setPhoneNumber(dto.getShippingAddress().getPhoneNumber());
        

        // Save Payment Details
        PaymentDetails payment = new PaymentDetails();
        payment.setPaymentMethod(dto.getPayment().getPaymentMethod());
        payment.setAmount(dto.getPayment().getAmount());
        payment.setTransactionId(dto.getPayment().getTransactionId() != null
                ? dto.getPayment().getTransactionId()
                : "TXN-" + System.currentTimeMillis());
        payment.setPaymentStatus("SUCCESS");
        paymentRepository.save(payment);

        // Create Order
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setStatus(OrderStatus.PLACED);
        order.setPaymentDetails(payment);
        order.setShippingAddress(address);
        orderRepository.save(order);

        // Add Order Items
        List<OrderItem> items = dto.getItems().stream().map(itemDto -> {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(itemDto.getPrice());
            return item;
        }).collect(Collectors.toList());
        orderItemRepository.saveAll(items);

        // Calculate total amount
        double total = items.stream().mapToDouble(i -> i.getQuantity() * i.getPrice()).sum();
        order.setTotalAmount(total);
        orderRepository.save(order);

        // Build Response DTO
        return mapToResponse(order, items, payment, address);
    }

    @Override
    public List<OrderResponseDto> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(order -> mapToResponse(
                        order,
                        orderItemRepository.findByOrderId(order.getId()),
                        order.getPaymentDetails(),
                        order.getShippingAddress()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
        // Returning as List because your interface defines List<OrderResponseDto>
        return List.of(mapToResponse(order, items, order.getPaymentDetails(), order.getShippingAddress()));
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Convert String status → Enum
        OrderStatus newStatus = OrderStatus.valueOf(status.toUpperCase());
        order.setStatus(newStatus);
        orderRepository.save(order);

        return getOrderById(orderId).get(0);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    // Helper method to map Entities → DTOs
    private OrderResponseDto mapToResponse(Order order, List<OrderItem> items, PaymentDetails payment,
                                           ShippingAddress address) {

        List<OrderItemResponseDto> itemDtos = items.stream()
                .map(i -> new OrderItemResponseDto(i.getId(), i.getProductId(), i.getQuantity(), i.getPrice()))
                .collect(Collectors.toList());

        PaymentResponseDto paymentDto = new PaymentResponseDto(
                payment.getPaymentMethod(),
                payment.getAmount(),
                payment.getTransactionId(),
                payment.getPaymentStatus()
        );

        ShippingAddressResponseDto addressDto = new ShippingAddressResponseDto(
                address.getName(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getCountry(),
                address.getPhoneNumber()
        );

        return new OrderResponseDto(
                order.getId(),
                order.getUserId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getOrderDate(),
                itemDtos,
                paymentDto,
                addressDto
        );
    }
}
