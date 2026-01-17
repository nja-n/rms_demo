package com.nesa.springboot_rms.kitchen.domain;

import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockIngredient {
    private Long id;
    private Ingredient ingredient;
    private double quantity;
    private String unit;
    private double price;
    private OutletId outletId;
}
