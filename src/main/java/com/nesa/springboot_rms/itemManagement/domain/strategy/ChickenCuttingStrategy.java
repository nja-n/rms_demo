package com.nesa.springboot_rms.itemManagement.domain.strategy;

import com.nesa.springboot_rms.itemManagement.domain.model.ChickenBatch;
import com.nesa.springboot_rms.itemManagement.domain.model.ChickenPiece;
import com.nesa.springboot_rms.itemManagement.domain.model.CuttingGoal;

import java.util.List;

public interface ChickenCuttingStrategy {
    List<ChickenPiece> cut(ChickenBatch batch, CuttingGoal goal);
    
    /**
     * Calculates how many whole chickens (of the given average weight) are needed
     * to fulfill the specific goal.
     */
    double calculateRequiredChickens(CuttingGoal goal, double avgChickenWeight);

    String getStrategyName();
}
