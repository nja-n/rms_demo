package com.nesa.springboot_rms.itemManagement.domain.model;

import java.util.List;

public class WeightBasedGoal implements CuttingGoal {
    private String pieceType;
    private double targetWeight;

    // 1. Mandatory No-args constructor for Jackson
    public WeightBasedGoal() {}

    public WeightBasedGoal(String pieceType, double targetWeight) {
        this.pieceType = pieceType;
        this.targetWeight = targetWeight;
    }

    @Override
    public List<ChickenPiece> calculate(ChickenBatch batch, CuttingRules rules) {
        // Business Logic: Check if the batch can actually provide this weight
        double maxPossibleYield = batch.getTotalWeight() * rules.yieldPercentage();

        if (targetWeight > maxPossibleYield) {
            throw new RuntimeException("Insufficient " + rules.pieceName() +
                    " weight in batch. Max available: " + maxPossibleYield);
        }

        // Return 1 bulk piece (Quantity 0 signifies weight-based bulk)
        return List.of(new ChickenPiece(rules.pieceName(), targetWeight, 0));
    }

    @Override
    public double estimateRequiredChickens(CuttingRules rules, double avgChickenWeight) {
        return targetWeight / (avgChickenWeight * rules.yieldPercentage());
    }

    @Override
    public String getPieceType() {
        return pieceType;
    }

    // 2. Setters for Jackson to inject values from JSON
    public void setPieceType(String pieceType) { this.pieceType = pieceType; }
    public void setTargetWeight(double targetWeight) { this.targetWeight = targetWeight; }

    // 3. Getters (Good practice for serializing responses back)
    public double getTargetWeight() { return targetWeight; }
}
