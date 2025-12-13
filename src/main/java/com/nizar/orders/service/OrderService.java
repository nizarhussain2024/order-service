package com.nizar.orders.service;

import com.nizar.orders.model.Order;
import com.nizar.orders.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderValidator validator;
    
    private final Map<String, Order> orders = new HashMap<>();
    private long orderIdCounter = 1;

    public Order createOrder(Order order) {
        validator.validateOrder(order);
        order.setId("ORD-" + orderIdCounter++);
        order.setStatus("PENDING");
        orders.put(order.getId(), order);
        return order;
    }

    public Optional<Order> getOrderById(String id) {
        return Optional.ofNullable(orders.get(id));
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public List<Order> getOrders(int page, int size, String status, String customerId) {
        List<Order> filtered = orders.values().stream()
            .filter(order -> status == null || order.getStatus().equals(status))
            .filter(order -> customerId == null || order.getCustomerId().equals(customerId))
            .sorted((a, b) -> b.getId().compareTo(a.getId()))
            .collect(Collectors.toList());
        
        int start = page * size;
        int end = Math.min(start + size, filtered.size());
        
        if (start >= filtered.size()) {
            return Collections.emptyList();
        }
        
        return filtered.subList(start, end);
    }

    public Order updateOrderStatus(String id, String status) {
        if (!validator.isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid order status: " + status);
        }
        Order order = orders.get(id);
        if (order != null) {
            order.setStatus(status);
        }
        return order;
    }

    public void deleteOrder(String id) {
        orders.remove(id);
    }

    public long getTotalOrders() {
        return orders.size();
    }

    public long getOrdersByStatus(String status) {
        return orders.values().stream()
            .filter(order -> order.getStatus().equals(status))
            .count();
    }
}
