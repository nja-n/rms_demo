package com.nesa.springboot_rms.outlet.domain;

import com.nesa.springboot_rms.outlet.domain.extention.HotelId;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;
import com.nesa.springboot_rms.outlet.domain.svb.LicenseNumber;
import com.nesa.springboot_rms.outlet.domain.svb.StarRating;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import com.nesa.springboot_rms.outlet.domain.enums.OutletType;
import com.nesa.springboot_rms.outlet.domain.enums.OwnershipType;

@Getter
@AllArgsConstructor
public class Hotel {
    private HotelId id; // Value Object
    private String name;
    private OwnershipType ownership; // Enum: PRIVATE, CORPORATE, FRANCHISE
    private LicenseNumber licenseNumber; // Value Object with Regex validation
    private StarRating stars; // Value Object (1-5)
    private List<OutletId> outletIds; // Reference to Outlets by ID (DDD Rule)

    // Business Logic: Only a 5-star hotel can have a Bar Outlet (Example Rule)
    public void addOutlet(OutletId outletId, OutletType type) {
        // if (type == OutletType.BAR && this.stars.getValue() < 4) {
        //     throw new DomainException("Only 4+ star hotels can manage Bar outlets.");
        // }
        this.outletIds.add(outletId);
    }
}
