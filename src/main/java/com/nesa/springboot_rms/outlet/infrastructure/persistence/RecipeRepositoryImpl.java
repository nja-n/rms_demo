package com.nesa.springboot_rms.outlet.infrastructure.persistence;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.nesa.springboot_rms.outlet.domain.Recipe;
import com.nesa.springboot_rms.outlet.domain.repo.RecipeRepository;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.RecipeEntity;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.repo.JpaRecipeRepo;
import com.nesa.springboot_rms.outlet.infrastructure.mapper.RecipePersistenceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepository {

    private final JpaRecipeRepo jpaRecipeRepo;
    private final RecipePersistenceMapper recipePersistenceMapper;
    
    @Override
    public Recipe save(Recipe recipe) {
        RecipeEntity recipeEntity = recipePersistenceMapper.toEntity(recipe);
        jpaRecipeRepo.save(recipeEntity);
        return recipePersistenceMapper.toDomain(recipeEntity);
    }

    @Override
    public List<Recipe> findAllByOutletId(Long outletId) {
        return jpaRecipeRepo.findAllByOutletId(outletId).stream()
            .map(recipePersistenceMapper::toDomain)
            .collect(Collectors.toList());
    }
}