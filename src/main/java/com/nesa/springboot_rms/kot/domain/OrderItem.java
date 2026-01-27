package com.nesa.springboot_rms.kot.domain;

public record OrderItem(Long dishId, String itemName, int quantity) {

    public OrderItem {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
    }
}
