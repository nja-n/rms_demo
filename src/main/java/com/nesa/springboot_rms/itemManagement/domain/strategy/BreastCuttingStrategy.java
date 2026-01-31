package com.nesa.springboot_rms.itemManagement.domain.strategy;

import com.nesa.springboot_rms.itemManagement.domain.model.*;

import java.util.List;

public class BreastCuttingStrategy implements ChickenCuttingStrategy{
    // We define the rules ONCE here
    private static final CuttingRules BREAST_RULES = new CuttingRules(0.30, 2, "BREAST");

    @Override
    public List<ChickenPiece> cut(ChickenBatch batch, CuttingGoal goal) {
        // NO IF-ELSE!
        // We just pass our rules to the goal, whatever type it is.
        return goal.calculate(batch, BREAST_RULES);
    }

    @Override
    public double calculateRequiredChickens(CuttingGoal goal, double avgChickenWeight) {
        return goal.estimateRequiredChickens(BREAST_RULES, avgChickenWeight);
    }

    @Override
    public String getStrategyName() { return "BREAST"; }
}
