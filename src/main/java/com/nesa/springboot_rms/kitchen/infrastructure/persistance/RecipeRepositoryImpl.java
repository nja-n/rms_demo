package com.nesa.springboot_rms.kitchen.infrastructure.persistance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.domain.Meal;
import com.nesa.springboot_rms.kitchen.domain.repo.RecipeRepository;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.IngredientEntity;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.MealEntity;
import com.nesa.springboot_rms.kitchen.infrastructure.repo.JpaIngredientRepo;
import com.nesa.springboot_rms.kitchen.infrastructure.repo.JpaMealRepo;
import com.nesa.springboot_rms.outlet.domain.Recipe;
import com.nesa.springboot_rms.outlet.domain.extention.RecipeId;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.RecipeEntity;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.repo.JpaRecipeRepo;
import com.nesa.springboot_rms.outlet.infrastructure.mapper.RecipePersistenceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepository {

    private final JpaRecipeRepo jpaRecipeRepo;
    private final RecipePersistenceMapper recipePersistenceMapper;
    private final JpaMealRepo jpaMealRepo;
    private final Mapper<Meal, MealEntity> mealMapper;
    
    
    
    @Override
    public Recipe save(Recipe recipe) {
        RecipeEntity recipeEntity = recipePersistenceMapper.toEntity(recipe);
        jpaRecipeRepo.save(recipeEntity);
        return recipePersistenceMapper.toDomain(recipeEntity);
    }

    @Override
    public List<Recipe> findAllByOutletId(Long outletId) {
        return jpaRecipeRepo.findAllByOutletId(outletId).stream()
            .map(recipePersistenceMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Recipe> findById(RecipeId id) {
        return jpaRecipeRepo.findById(id.getValue()).map(recipePersistenceMapper::toDomain);
    }

    @Override
    public void saveMeal(Meal meal) {
        MealEntity mealEntity = mealMapper.toEntity(meal);
        jpaMealRepo.save(mealEntity);
    }

    @Override
    public List<Meal> getMeals(String id) {
        if(id != null) {
            MealEntity mealEntity = jpaMealRepo.findById(Long.parseLong(id)).orElse(null);
            return List.of(mealMapper.toDomain(mealEntity));
        } else {
            return jpaMealRepo.findAll().stream()
            .map(mealMapper::toDomain)
            .collect(Collectors.toList());
        }
    }
}