package com.nesa.springboot_rms.kitchen.domain.repo;

import java.util.List;
import java.util.Optional;

import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.domain.Meal;
import com.nesa.springboot_rms.outlet.domain.Recipe;
import com.nesa.springboot_rms.outlet.domain.extention.RecipeId;

public interface RecipeRepository {
    List<Recipe> findAllByOutletId(Long outletId);
    Recipe save(Recipe recipe);
    Optional<Recipe> findById(RecipeId id);
    
    void saveMeal(Meal meal);
    List<Meal> getMeals(String id);
}
