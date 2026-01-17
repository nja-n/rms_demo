package com.nesa.springboot_rms.kitchen.domain.repo;

import java.util.List;

import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.domain.StockIngredient;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

public interface IngredientRepository {
    
    void saveAll(List<Ingredient> list);
    // List<Ingredient> getAll();
    Ingredient getByName(String name);

    List<StockIngredient> findCurrentStockByOutletId(OutletId outletId);
    List<Ingredient> getAllByOutletId(OutletId outletId);
    StockIngredient getById(Long id);
}
