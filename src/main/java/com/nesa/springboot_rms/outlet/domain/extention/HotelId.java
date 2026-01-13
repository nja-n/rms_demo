package com.nesa.springboot_rms.outlet.domain.extention;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nesa.springboot_rms.common.supporter.Id;

public class HotelId extends Id {
    public HotelId(Long value) {
        super(value);
    }

    @JsonCreator
    public HotelId(Object value) {
        super(value);
    }
}
