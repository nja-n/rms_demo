package com.nesa.springboot_rms.table.Domain;

public enum TableStatus {
    FREE,
    OCCUPIED,
    CLEANING;

    public static TableStatus fromString(String status) {
        return TableStatus.valueOf(status.toUpperCase());
    }
}
