package com.nesa.springboot_rms.item_demo.domain.prep_aggregate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;

@Getter
public class CuttingTemplate {
    private Long templateId;
    private String name;
    private List<OutputRule> outputRules;

    public CuttingTemplate(Long templateId, String name, List<OutputRule> outputRules) {
        this.templateId = templateId;
        this.name = name;
        this.outputRules = new ArrayList<>(outputRules != null ? outputRules : Collections.emptyList());
        validateTotalYield();
    }

    public void validateTotalYield() {
        double totalPercentage = outputRules.stream()
                .mapToDouble(OutputRule::getPercentage)
                .sum();

        // Business Rule: Total percentage should not exceed 100%
        // (It could be less if there is unaccounted loss, but strict rule usually means
        // == 100 or <= 100)
        if (totalPercentage > 100.0) {
            throw new IllegalStateException(
                    "Total output percentage (" + totalPercentage + "%) exceeds 100% for Template: " + name);
        }
    }

    // Domain behavior to add rule if needed, maintaining invariants
    public void addRule(OutputRule rule) {
        this.outputRules.add(rule);
        validateTotalYield();
    }
}
