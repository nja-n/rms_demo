package com.nesa.springboot_rms.outlet.infrastructure.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nesa.springboot_rms.outlet.infrastructure.jpa.HotelEntity;

public interface JpaHotelRepo extends JpaRepository<HotelEntity, Long> {
    
}
