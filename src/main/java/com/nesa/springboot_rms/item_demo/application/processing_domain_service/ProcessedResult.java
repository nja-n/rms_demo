package com.nesa.springboot_rms.item_demo.application.processing_domain_service;

import java.util.List;

import com.nesa.springboot_rms.item_demo.domain.Item.inventory_context.RawIngredient;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProcessedResult {
    private final Double totalWasteWeight;
    private final List<RawIngredient> processedIngredients;

    public ProcessedResult(Double totalWasteWeight, List<RawIngredient> processedIngredients) {
        if (totalWasteWeight < 0) {
            throw new IllegalArgumentException("Waste weight cannot be negative");
        }
        this.totalWasteWeight = totalWasteWeight;
        this.processedIngredients = processedIngredients;
    }
}
