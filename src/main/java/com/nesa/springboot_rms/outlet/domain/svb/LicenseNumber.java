package com.nesa.springboot_rms.outlet.domain.svb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class LicenseNumber {
    private String value;

    public LicenseNumber(String value) {
        this.value = value;
    }

    @JsonCreator
    public static LicenseNumber of(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("License number cannot be empty");
        }
        // Add your specific business logic/regex here if needed
        return new LicenseNumber(value);
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
