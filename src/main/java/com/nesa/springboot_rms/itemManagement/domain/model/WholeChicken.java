package com.nesa.springboot_rms.itemManagement.domain.model;

import java.util.UUID;

public class WholeChicken {

    private final UUID id;
    private final double totalWeight;

    public WholeChicken(double totalWeight) {
        this.id = UUID.randomUUID();
        this.totalWeight = totalWeight;
    }

    public UUID getId() { return id; }
    public double getTotalWeight() { return totalWeight; }
}
