package com.nizar.orders.service;

import com.nizar.orders.model.Order;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private final Map<String, Order> orders = new HashMap<>();
    private long orderIdCounter = 1;

    public Order createOrder(Order order) {
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

    public Order updateOrderStatus(String id, String status) {
        Order order = orders.get(id);
        if (order != null) {
            order.setStatus(status);
        }
        return order;
    }

    public void deleteOrder(String id) {
        orders.remove(id);
    }
}


