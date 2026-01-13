package com.nesa.springboot_rms.outlet.infrastructure.jpa.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nesa.springboot_rms.outlet.infrastructure.jpa.RecipeEntity;

public interface JpaRecipeRepo extends JpaRepository<RecipeEntity, Long> {

    Collection<RecipeEntity> findAllByOutletId(Long outletId);
    
}
