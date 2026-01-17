package com.nesa.springboot_rms.kitchen.infrastructure.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nesa.springboot_rms.kitchen.infrastructure.entity.MealEntity;

public interface JpaMealRepo extends JpaRepository<MealEntity, Long> {
    
}
