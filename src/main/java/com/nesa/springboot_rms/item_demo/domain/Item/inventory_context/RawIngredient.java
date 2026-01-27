package com.nesa.springboot_rms.item_demo.domain.Item.inventory_context;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RawIngredient {
    private Long itemId;
    private String name;
    private Double currentWeight;

    // Domain method to reduce stock/weight could go here
    public void consume(Double amount) {
        if (amount > currentWeight) {
            throw new IllegalArgumentException("Not enough ingredient stock");
        }
        this.currentWeight -= amount;
    }
}
