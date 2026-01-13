package com.nesa.springboot_rms.outlet.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.nesa.springboot_rms.common.utils.Mapper;
import com.nesa.springboot_rms.outlet.domain.Outlet;
import com.nesa.springboot_rms.outlet.domain.enums.CommonStatus;
import com.nesa.springboot_rms.outlet.domain.enums.OutletType;
import com.nesa.springboot_rms.outlet.domain.extention.HotelId;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.HotelEntity;
import com.nesa.springboot_rms.outlet.infrastructure.jpa.OutletEntity;

@Component
public class OutletPersistenceMapper implements Mapper<Outlet, OutletEntity> {

    @Override
    public OutletEntity toEntity(Outlet domain) {
        OutletEntity entity = new OutletEntity();

        entity.setId(domain.getId().getValue());
        
        // Core Details
        entity.setOutletName(domain.getName());
        entity.setOutletCode(domain.getCode());
        entity.setOutletType(domain.getType().name());
        entity.setHotel(new HotelEntity());
        entity.getHotel().setId(domain.getHotelId().getValue());
        
        // Status
        entity.setStatus(domain.getStatus().name());
        entity.setIsActive(domain.getIsActive());

        // Note: The 'hotel' reference (ManyToOne) should be set in the 
        // Repository implementation after fetching the HotelEntity by ID.
        
        return entity;
    }

    @Override
    public Outlet toDomain(OutletEntity entity) {
        return new Outlet(
            new OutletId(entity.getId()),
            new HotelId(entity.getHotel().getId()),
            entity.getOutletName(),
            entity.getOutletCode(),
            OutletType.valueOf(entity.getOutletType()),
            CommonStatus.valueOf(entity.getStatus()),
            entity.getIsActive()
        );
    }
    
}
