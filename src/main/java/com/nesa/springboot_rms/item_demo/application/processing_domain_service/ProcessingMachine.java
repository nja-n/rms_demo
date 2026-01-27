package com.nesa.springboot_rms.item_demo.application.processing_domain_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nesa.springboot_rms.item_demo.domain.Item.inventory_context.RawIngredient;
import com.nesa.springboot_rms.item_demo.domain.prep_aggregate.CuttingTemplate;
import com.nesa.springboot_rms.item_demo.domain.prep_aggregate.OutputRule;

@Service
public class ProcessingMachine {

    /**
     * core domain logic:
     * Calculates waste based on the CuttingTemplate applied to the RawIngredient.
     */
    public ProcessedResult process(RawIngredient rawIngredient, CuttingTemplate template) {
        if (rawIngredient == null || template == null) {
            throw new IllegalArgumentException("Ingredient and Template are required for processing");
        }

        double totalInputWeight = rawIngredient.getCurrentWeight();
        double totalWaste = 0.0;

        List<RawIngredient> processedIngredients = new ArrayList<>();
        for (OutputRule rule : template.getOutputRules()) {
            if (Boolean.TRUE.equals(rule.getIsWaste())) {
                double wastePortion = (rule.getPercentage() / 100.0) * totalInputWeight;
                totalWaste += wastePortion;
            }
            processedIngredients.add(new RawIngredient(rule.getRuleId(), rule.getPartName(), (rule.getPercentage() / 100.0) * totalInputWeight));
        }

        // Return the result (Value Object)
        return new ProcessedResult(totalWaste, processedIngredients);
    }
}
