package com.nesa.springboot_rms.kitchen.infrastructure.persistance;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.domain.StockIngredient;
import com.nesa.springboot_rms.kitchen.domain.repo.IngredientRepository;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.IngredientEntity;
import com.nesa.springboot_rms.kitchen.infrastructure.entity.StockIngredientEntity;
import com.nesa.springboot_rms.kitchen.infrastructure.repo.JpaIngredientRepo;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class IngredientRepositoryImpl implements IngredientRepository {

    private final Mapper<Ingredient, IngredientEntity> mapper;
    private final JpaIngredientRepo jpaIngredientRepo;
    private final Mapper<StockIngredient, StockIngredientEntity> stockIngredientMapper;

    @Override
    public void saveAll(List<Ingredient> list) {
        List<IngredientEntity> ingredientEntities = list.stream()
            .map(mapper::toEntity)
            .collect(Collectors.toList());
        jpaIngredientRepo.saveAll(ingredientEntities);
    }

    // @Override
    // public List<Ingredient> getAll() {
    //     return jpaIngredientRepo.findAll().stream()
    //         .map(mapper::toDomain)
    //         .collect(Collectors.toList());
    // }

    @Override
    public Ingredient getByName(String name) {
        IngredientEntity ingredientEntity = jpaIngredientRepo.findByName(name);
        return mapper.toDomain(ingredientEntity);
    }

    @Override
    public List<StockIngredient> findCurrentStockByOutletId(OutletId outletId) {
        List<StockIngredientEntity> stockIngredientEntities = jpaIngredientRepo.findCurrentStockByOutletId(outletId.getValue());
        return stockIngredientEntities.stream()
            .map(stockIngredientMapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Ingredient> getAllByOutletId(OutletId outletId) {
        Long outletIdValue = outletId.getValue();
        List<IngredientEntity> ingredientEntities = jpaIngredientRepo.findAllByOutletId(outletIdValue);
        return ingredientEntities.stream()
            .map(mapper::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public StockIngredient getById(Long id) {
        StockIngredientEntity stockIngredientEntity = jpaIngredientRepo.findById(id);
        return stockIngredientMapper.toDomain(stockIngredientEntity);
    }
}
