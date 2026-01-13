package com.nesa.springboot_rms.outlet.domain;

import com.nesa.springboot_rms.outlet.domain.enums.CommonStatus;
import com.nesa.springboot_rms.outlet.domain.enums.OutletType;
import com.nesa.springboot_rms.outlet.domain.extention.HotelId;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Outlet {
    private OutletId id;
    private HotelId hotelId;
    private String name;
    private String code;
    private OutletType type;
    private CommonStatus status;
    private Boolean isActive;
    
    public void toggleActive() {
        this.isActive = !this.isActive;
        this.status = this.isActive ? CommonStatus.ACTIVE : CommonStatus.INACTIVE;
    }
}