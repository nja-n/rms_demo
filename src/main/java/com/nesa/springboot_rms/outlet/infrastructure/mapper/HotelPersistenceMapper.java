package com.nesa.springboot_rms.outlet.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.outlet.domain.Hotel;
import com.nesa.springboot_rms.outlet.domain.enums.OwnershipType;
import com.nesa.springboot_rms.outlet.domain.extention.HotelId;
import com.nesa.springboot_rms.outlet.domain.svb.LicenseNumber;
import com.nesa.springboot_rms.outlet.domain.svb.StarRating;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.HotelEntity;

@Component
public class HotelPersistenceMapper implements Mapper<Hotel, HotelEntity> {

    @Override
    public HotelEntity toEntity(Hotel domain) {
        HotelEntity entity = new HotelEntity();
        entity.setName(domain.getName());
        entity.setOwnership(domain.getOwnership().toString());
        entity.setLicenseNumber(domain.getLicenseNumber().toString());
        entity.setStars(domain.getStars().getValue());
        return entity;
    }

    @Override
    public Hotel toDomain(HotelEntity entity) {
        return new Hotel(
                new HotelId(entity.getId()),
                entity.getName(),
                OwnershipType.of(entity.getOwnership()),
                LicenseNumber.of(entity.getLicenseNumber()), // Re-validating via Value Object
                StarRating.fromInt(entity.getStars()),
                null);
    }
}
