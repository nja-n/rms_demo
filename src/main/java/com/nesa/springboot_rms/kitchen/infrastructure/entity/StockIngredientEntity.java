package com.nesa.springboot_rms.kitchen.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "stock_ingredient")
@Builder
@AllArgsConstructor
public class StockIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ingredientId;
    private Long outletId;
    private double quantity;
    private String unit;
    private double price;
}
