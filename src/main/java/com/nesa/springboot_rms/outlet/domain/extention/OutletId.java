package com.nesa.springboot_rms.outlet.domain.extention;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.nesa.springboot_rms.common.supporter.Id;

public class OutletId extends Id {
    public OutletId(Long value) {
        super(value);
    }

    @JsonCreator
    public OutletId(Object value) {
        super(value);
    }
}
