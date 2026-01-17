package com.nesa.springboot_rms.kitchen.infrastructure.persistance.mapper;

import org.springframework.stereotype.Component;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.IngredientEntity;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

@Component
public class IngredientMapper implements Mapper<Ingredient, IngredientEntity>{
    
    @Override
    public Ingredient toDomain(IngredientEntity entity) {
        return new Ingredient(entity.getId(), entity.getName(), entity.getDescription(), entity.getThumbnail(), entity.getType(), 
            new OutletId(entity.getOutletId()));
    }

    @Override
    public IngredientEntity toEntity(Ingredient domain) {
        // IngredientEntity entity = new IngredientEntity();
        // entity.setId(domain.getIdIngredient());
        // entity.setName(domain.getStrIngredient());
        // entity.setDescription(domain.getStrDescription());
        // entity.setThumbnail(domain.getStrThumb());
        // entity.setType(domain.getStrType());
        // return entity;

        return new IngredientEntity(domain.getId(), domain.getName(), domain.getDescription(), domain.getThumbnail(), domain.getType(), domain.getOutletId().getValue());
        
    }
}
