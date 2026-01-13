package com.nesa.springboot_rms.outlet.domain.repo;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nesa.springboot_rms.common.supporter.Id;
import com.nesa.springboot_rms.outlet.domain.Hotel;

@Repository
public interface HotelRepository {
    Hotel save(Hotel hotel);
    Optional<Hotel> findById(Id id);
}
