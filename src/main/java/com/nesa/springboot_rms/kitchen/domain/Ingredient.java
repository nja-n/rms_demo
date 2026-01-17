package com.nesa.springboot_rms.kitchen.domain;

import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Ingredient {
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private String type;
    private OutletId outletId;

    // private String strIngredient;
    // private String strDescription;
    // private String strThumb;
    // private String strType;
}
