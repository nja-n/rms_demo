package com.nesa.springboot_rms.outlet.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nesa.springboot_rms.kitchen.domain.repo.RecipeRepository;
import com.nesa.springboot_rms.outlet.domain.Recipe;
import com.nesa.springboot_rms.outlet.domain.RecipeIngredient;
import com.nesa.springboot_rms.outlet.domain.extention.RecipeId;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeApplicationService {
    private final RecipeRepository recipeRepository;

    public List<Recipe> getAllRecipes(Long outletId) {
        return recipeRepository.findAllByOutletId(outletId);
    }

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe get(RecipeId id) {
        return recipeRepository.findById(id).orElse(null);
    }
    
}
