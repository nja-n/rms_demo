package com.nesa.springboot_rms.kitchen.infrastructure.persistance.mapper;

import org.springframework.stereotype.Component;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.domain.StockIngredient;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.StockIngredientEntity;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

@Component
public class StockIngredientMapper implements Mapper<StockIngredient, StockIngredientEntity> {
    
    @Override
    public StockIngredient toDomain(StockIngredientEntity entity) {
        Ingredient ingredient = Ingredient.builder()
            .id(entity.getIngredientId())
            .build();
        return StockIngredient.builder()
            .id(entity.getId())
            .ingredient(ingredient)
            .quantity(entity.getQuantity())
            .unit(entity.getUnit())
            .price(entity.getPrice())
            .outletId(new OutletId(entity.getOutletId()))
            .build();
    }

    @Override
    public StockIngredientEntity toEntity(StockIngredient domain) {
        return StockIngredientEntity.builder()
            .id(domain.getId())
            .ingredientId(domain.getIngredient().getId())
            .quantity(domain.getQuantity())
            .unit(domain.getUnit())
            .price(domain.getPrice())
            .outletId(domain.getOutletId().getValue())
            .build();
    }
    
}
