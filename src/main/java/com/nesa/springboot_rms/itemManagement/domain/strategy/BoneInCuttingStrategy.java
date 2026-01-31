package com.nesa.springboot_rms.itemManagement.domain.strategy;

import com.nesa.springboot_rms.itemManagement.domain.model.*;

import java.util.List;

public class BoneInCuttingStrategy implements ChickenCuttingStrategy{
    // 1. 90% Yield (10% is skin/blood/waste loss)
    // 2. 12 Pieces per whole chicken (Standard curry cut)
    private static final CuttingRules BONE_IN_RULES = new CuttingRules(0.90, 12, "BONE_IN");

    @Override
    public List<ChickenPiece> cut(ChickenBatch batch, CuttingGoal goal) {
        // DOUBLE DISPATCH:
        // We don't ask "Is it weight or qty?"
        // We tell the goal: "Here are the rules for Bone-In. You calculate it."
        return goal.calculate(batch, BONE_IN_RULES);
    }

    @Override
    public double calculateRequiredChickens(CuttingGoal goal, double avgChickenWeight) {
        return goal.estimateRequiredChickens(BONE_IN_RULES, avgChickenWeight);
    }

    @Override
    public String getStrategyName() {
        return "BONE_IN";
    }
}
