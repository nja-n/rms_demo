package com.nesa.springboot_rms.outlet.infrastructure.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.outlet.domain.Recipe;
import com.nesa.springboot_rms.outlet.domain.RecipeIngredient;
import com.nesa.springboot_rms.outlet.domain.enums.CommonStatus;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import com.nesa.springboot_rms.outlet.domain.extention.RecipeId;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.OutletEntity;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.RecipeEntity;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.RecipeIngredientEntity;

@Component
public class RecipePersistenceMapper implements Mapper<Recipe, RecipeEntity> {
    
    @Override
    public RecipeEntity toEntity(Recipe source) {
        if (source == null) return null;

        RecipeEntity entity = new RecipeEntity();
        
        // Map basic fields
        if (source.getId() != null) {
            entity.setId(source.getId().getValue());
        }
        entity.setName(source.getRecipeName());
        entity.setPreparationMethod(source.getMethod());
        entity.setPreparationTime(source.getHeatTimeMinutes());
        entity.setStatus(source.getStatus() != null ? source.getStatus().name() : null);

        // Map Outlet (Reference by Identity)
        if (source.getOutletId() != null) {
            OutletEntity outletProxy = new OutletEntity();
            outletProxy.setId(source.getOutletId().getValue());
            entity.setOutlet(outletProxy);
        }

        // Map Ingredients List (Bidirectional mapping)
        if (source.getIngredients() != null) {
            List<RecipeIngredientEntity> ingredientEntities = source.getIngredients().stream()
                .map(domainIng -> {
                    RecipeIngredientEntity ingEntity = new RecipeIngredientEntity();
                    ingEntity.setAssetId(Long.parseLong(domainIng.getAssetId()));
                    ingEntity.setQuantity(domainIng.getQuantity().intValue());
                    ingEntity.setUnit(domainIng.getUnit());
                    // IMPORTANT: Set the parent reference for JPA cascade
                    ingEntity.setRecipe(entity); 
                    return ingEntity;
                }).collect(Collectors.toList());
            
            entity.setIngredients(ingredientEntities);
        }

        return entity;
    }

    @Override
    public Recipe toDomain(RecipeEntity source) {
        if (source == null) return null;

        return Recipe.builder()
            .id(new RecipeId(source.getId()))
            .outletId(new OutletId(source.getOutlet().getId()))
            .recipeName(source.getName())
            .method(source.getPreparationMethod())
            .heatTimeMinutes(source.getPreparationTime())
            .status(source.getStatus() != null ? CommonStatus.valueOf(source.getStatus()) : null)
            .ingredients(mapIngredientsToDomain(source.getIngredients()))
            .build();
    }

    private List<RecipeIngredient> mapIngredientsToDomain(List<RecipeIngredientEntity> entities) {
        if (entities == null) return new ArrayList<>();
        return entities.stream()
            .map(entity -> new RecipeIngredient(
                entity.getAssetId().toString(),
                entity.getQuantity().doubleValue(),
                entity.getUnit()
            )).collect(Collectors.toList());
    }
    
}
