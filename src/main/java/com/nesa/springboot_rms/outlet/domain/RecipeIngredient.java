package com.nesa.springboot_rms.outlet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecipeIngredient {
    private String assetId; // Raw Material ID
    private Double quantity;
    private String unit; // g, ml, pcs
}
