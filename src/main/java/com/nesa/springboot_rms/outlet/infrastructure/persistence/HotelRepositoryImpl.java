package com.nesa.springboot_rms.outlet.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nesa.springboot_rms.common.supporter.Id;
import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.outlet.domain.Hotel;
import com.nesa.springboot_rms.outlet.domain.repo.HotelRepository;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.HotelEntity;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.repo.JpaHotelRepo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HotelRepositoryImpl implements HotelRepository {
    
    private final JpaHotelRepo jpaHotelRepo;
    private final Mapper<Hotel, HotelEntity> mapper;
    @Override
    public Hotel save(Hotel hotel) {
        HotelEntity hotelEntity = mapper.toEntity(hotel);
        return mapper.toDomain(jpaHotelRepo.save(hotelEntity));
    }

    @Override
    public Optional<Hotel> findById(Id id) {
        return jpaHotelRepo.findById(id.getValue()).map(hotelEntity -> mapper.toDomain(hotelEntity));
    }
    
}