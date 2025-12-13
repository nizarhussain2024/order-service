package com.nizar.orders.validator;

import com.nizar.orders.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    
    public void validateOrder(Order order) {
        if (order.getCustomerId() == null || order.getCustomerId().isEmpty()) {
            throw new IllegalArgumentException("Customer ID is required");
        }
        
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }
        
        for (var item : order.getItems()) {
            if (item.getProductId() == null || item.getProductId().isEmpty()) {
                throw new IllegalArgumentException("Product ID is required for all items");
            }
            
            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                throw new IllegalArgumentException("Item quantity must be greater than 0");
            }
            
            if (item.getPrice() == null || item.getPrice() <= 0) {
                throw new IllegalArgumentException("Item price must be greater than 0");
            }
        }
    }
    
    public boolean isValidStatus(String status) {
        return status != null && 
               (status.equals("PENDING") || 
                status.equals("CONFIRMED") || 
                status.equals("SHIPPED") || 
                status.equals("DELIVERED") || 
                status.equals("CANCELLED"));
    }
}

