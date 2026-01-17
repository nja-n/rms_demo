package com.nesa.springboot_rms.outlet.domain.extention;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nesa.springboot_rms.common.supporter.Id;

public class RecipeId extends Id {
    public RecipeId(Long value) {
        super(value);
    }

    @JsonCreator
    public RecipeId(Object value) {
        super(value);
    }
}
