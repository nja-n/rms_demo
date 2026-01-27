package com.nesa.springboot_rms.kot.infrastructure;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEmbeddable {

    private Long dishId;
    private String itemName;
    private int quantity;
}
