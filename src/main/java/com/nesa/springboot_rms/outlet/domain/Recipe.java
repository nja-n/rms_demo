package com.nesa.springboot_rms.outlet.domain;

import java.util.List;

import com.nesa.springboot_rms.outlet.domain.enums.CommonStatus;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import com.nesa.springboot_rms.outlet.domain.extention.RecipeId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Recipe {
    private RecipeId id;
    private OutletId outletId; // Reference by Identity
    private String recipeName;
    private String method; // Instructions
    private Integer heatTimeMinutes;
    private List<RecipeIngredient> ingredients; // The "Row Ingredients"
    private CommonStatus status;
}
