package com.nesa.springboot_rms.itemManagement.domain.model;

import java.util.List;

public record ChickenBatch(List<WholeChicken> chickens) {

    public double getTotalWeight() {
        return chickens.stream().mapToDouble(WholeChicken::getTotalWeight).sum();
    }

    public int getCount() {
        return chickens.size();
    }
}
