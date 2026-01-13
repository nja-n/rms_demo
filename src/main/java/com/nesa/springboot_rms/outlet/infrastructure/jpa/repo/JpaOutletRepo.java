package com.nesa.springboot_rms.outlet.infrastructure.jpa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nesa.springboot_rms.outlet.infrastructure.jpa.OutletEntity;

@Repository
public interface JpaOutletRepo extends JpaRepository<OutletEntity, Long>{

    List<OutletEntity> findAllByHotelId(Long hotelId);
    
}
