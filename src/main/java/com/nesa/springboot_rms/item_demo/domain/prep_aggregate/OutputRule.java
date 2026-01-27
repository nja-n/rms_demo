package com.nesa.springboot_rms.item_demo.domain.prep_aggregate;

import lombok.Getter;

@Getter
public class OutputRule {
    private Long ruleId;
    private String partName;
    private Double percentage; // 0.0 to 100.0
    private Boolean isWaste;

    public OutputRule(Long ruleId, String partName, Double percentage, Boolean isWaste) {
        if (percentage < 0) {
            throw new IllegalArgumentException("Percentage cannot be negative");
        }
        this.ruleId = ruleId;
        this.partName = partName;
        this.percentage = percentage;
        this.isWaste = isWaste;
    }
}
