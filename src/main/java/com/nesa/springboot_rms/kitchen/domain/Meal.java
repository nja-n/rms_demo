package com.nesa.springboot_rms.kitchen.domain;

import java.util.ArrayList;
import java.util.List;

import com.nesa.springboot_rms.kitchen.domain.meal.MealIngredient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    private Long id;
    private String name;
    private String category;
    private String area;
    private String instructions;
    private String thumbnail;
    private String youtube;
    private String source;

    private List<MealIngredient> mealIngredients;

    public void addMealIngredient(MealIngredient mealIngredient) {
        if (this.mealIngredients == null) {
            this.mealIngredients = new ArrayList<>();
        }
        this.mealIngredients.add(mealIngredient);
    }
}
