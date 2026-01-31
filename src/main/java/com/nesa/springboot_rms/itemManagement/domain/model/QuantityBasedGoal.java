package com.nesa.springboot_rms.itemManagement.domain.model;

import java.util.List;

public class QuantityBasedGoal implements CuttingGoal {


    private String pieceType;
    private int targetQty;

    public QuantityBasedGoal() {
    }

    public QuantityBasedGoal(String pieceType, int targetQty) {
        this.pieceType = pieceType;
        this.targetQty = targetQty;
    }

    @Override
    public List<ChickenPiece> calculate(ChickenBatch batch, CuttingRules rules) {
        int maxQty = batch.getCount() * rules.piecesPerWholeChicken();
        if (targetQty > maxQty) {
            throw new RuntimeException("Insufficient chickens for " + targetQty + " " + rules.pieceName());
        }
        double weight = (batch.getTotalWeight() * rules.yieldPercentage() / maxQty) * targetQty;
        return List.of(new ChickenPiece(rules.pieceName(), weight, targetQty));
    }

    @Override
    public double estimateRequiredChickens(CuttingRules rules, double avgChickenWeight) {
        return (double) targetQty / rules.piecesPerWholeChicken();
    }

    @Override
    public String getPieceType() {
        return pieceType;
    }

    // Getters and Setters for Jackson
    public void setPieceType(String pieceType) {
        this.pieceType = pieceType;
    }

    public void setTargetQty(int targetQty) {
        this.targetQty = targetQty;
    }
}
