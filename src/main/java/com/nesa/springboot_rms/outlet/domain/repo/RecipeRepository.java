package com.nesa.springboot_rms.outlet.domain.repo;

import java.util.List;

import com.nesa.springboot_rms.outlet.domain.Recipe;

public interface RecipeRepository {
    List<Recipe> findAllByOutletId(Long outletId);
    Recipe save(Recipe recipe);
}
