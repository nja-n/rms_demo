package com.nesa.springboot_rms.outlet.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OwnershipType {
    PRIVATE, CORPORATE, FRANCHISE;

    @JsonCreator
    public static OwnershipType fromString(String value) {
        if (value == null) return null;
        try {
            return OwnershipType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown ownership type: " + value);
        }
    }

    public static OwnershipType of(String value) {
        return OwnershipType.fromString(value);
    }
}
