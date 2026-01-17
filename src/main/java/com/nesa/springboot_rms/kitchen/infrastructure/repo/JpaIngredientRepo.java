package com.nesa.springboot_rms.kitchen.infrastructure.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nesa.springboot_rms.kitchen.infrastructure.entity.IngredientEntity;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.StockIngredientEntity;

public interface JpaIngredientRepo extends JpaRepository<IngredientEntity, Integer> {

    IngredientEntity findByName(String name);

    @Query("SELECT s FROM StockIngredientEntity s WHERE s.outletId = :outletId")
    List<StockIngredientEntity> findCurrentStockByOutletId(Long outletId);

    List<IngredientEntity> findAllByOutletId(Long outletId);

    @Query("SELECT s FROM StockIngredientEntity s WHERE s.id = :id")
    StockIngredientEntity findById(Long id);

}
