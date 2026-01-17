package com.nesa.springboot_rms.kitchen.infrastructure.persistance.mapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.domain.Meal;
import com.nesa.springboot_rms.kitchen.domain.meal.MealIngredient;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.IngredientEntity;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.MealEntity;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.MealIngredientEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MealMapper implements Mapper<Meal, MealEntity> {

    private final IngredientMapper ingredientMapper;

    @Override
    public MealEntity toEntity(Meal domain) {
        MealEntity entity = new MealEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setCategory(domain.getCategory());
        entity.setArea(domain.getArea());
        entity.setInstructions(domain.getInstructions());
        entity.setThumbnail(domain.getThumbnail());
        entity.setYoutube(domain.getYoutube());
        entity.setSource(domain.getSource());
        entity.setMealIngredients(domain
                .getMealIngredients()
                .stream()
                .map(x -> {
                    return toEntity(x, entity);
                })
                .collect(Collectors.toList()));
        return entity;
    }

    @Override
    public Meal toDomain(MealEntity entity) {
        if (entity == null)
            return null;

        Meal meal = Meal.builder()
                .id(entity.getId())
                .name(entity.getName())
                .category(entity.getCategory())
                .area(entity.getArea())
                .instructions(entity.getInstructions()) // ✅ FIXED
                .thumbnail(entity.getThumbnail())
                .youtube(entity.getYoutube())
                .source(entity.getSource())
                .mealIngredients(new ArrayList<>())
                .build();

        if (entity.getMealIngredients() != null) {
            entity.getMealIngredients()
                    .forEach(mi -> meal.addMealIngredient(toDomain(mi, meal)));
        }

        return meal;
    }

    private MealIngredientEntity toEntity(MealIngredient domain, MealEntity parentMeal) {
        if (domain == null)
            return null;

        MealIngredientEntity entity = new MealIngredientEntity();
        entity.setId(domain.getId());
        entity.setMeal(parentMeal); // Domain Meal (as per your entity)
        entity.setIngredient(ingredientMapper.toEntity(domain.getIngredient()));
        entity.setQuantity(domain.getMeasure());
        // entity.setUnit(domain.getUnit());

        return entity;
    }

    private MealIngredient toDomain(MealIngredientEntity entity, Meal parentMeal) {
        if (entity == null)
            return null;

        return MealIngredient.builder()
                .id(entity.getId())
                // .meal(parentMeal)
                .ingredient(ingredientMapper.toDomain(entity.getIngredient()))
                .measure(entity.getQuantity())
                .build();
    }

    // private IngredientEntity toEntity(Ingredient domain) {
    //     if (domain == null)
    //         return null;

    //     IngredientEntity entity = new IngredientEntity();
    //     entity.setId(domain.getId());
    //     entity.setName(domain.getName());
    //     entity.setDescription(domain.getDescription());
    //     entity.setThumbnail(domain.getThumbnail());
    //     entity.setType(domain.getType());
    //     return entity;
    // }

    // private Ingredient toDomain(IngredientEntity entity) {
    //     if (entity == null)
    //         return null;

    //     return Ingredient.builder()
    //             .id(entity.getId())
    //             .name(entity.getName())
    //             .description(entity.getDescription())
    //             .thumbnail(entity.getThumbnail())
    //             .type(entity.getType())
    //             .build();
    // }

}
