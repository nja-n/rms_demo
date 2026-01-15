package com.nesa.springboot_rms.table.Domain;

public record TableId(Long value) {
    public TableId {
        if (value == null) {
            throw new IllegalArgumentException("TableId cannot be null");
        }
    }
}
