package com.nesa.springboot_rms.kitchen.domain.meal;

import com.nesa.springboot_rms.kitchen.domain.Ingredient;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MealIngredient {
    private Long id;
    // private Long mealId;
    // private Long ingredientId;
    private Ingredient ingredient;
    // private Meal meal;
    private String measure;
}
