package com.nesa.springboot_rms.itemManagement.domain.strategy;

import com.nesa.springboot_rms.itemManagement.domain.model.*;

import java.util.List;

public class WingCuttingStrategy implements ChickenCuttingStrategy{

    private static final CuttingRules WING_RULES = new CuttingRules(0.12, 2, "WINGS");

    @Override
    public List<ChickenPiece> cut(ChickenBatch batch, CuttingGoal goal) {
        return goal.calculate(batch, WING_RULES);
    }

    @Override
    public double calculateRequiredChickens(CuttingGoal goal, double avgChickenWeight) {
        return goal.estimateRequiredChickens(WING_RULES, avgChickenWeight);
    }

    @Override
    public String getStrategyName() { return "WINGS"; }

}
