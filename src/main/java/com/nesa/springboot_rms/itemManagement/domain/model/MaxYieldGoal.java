package com.nesa.springboot_rms.itemManagement.domain.model;

import java.util.List;

/**
 * A special goal that represents cutting the maximum possible yield for a given piece type.
 * This is used when a user provides chickens but no specific goals.
 */
public class
MaxYieldGoal implements CuttingGoal {

    private final String pieceType;

    public MaxYieldGoal(String pieceType) {
        this.pieceType = pieceType;
    }

    @Override
    public List<ChickenPiece> calculate(ChickenBatch batch, CuttingRules rules) {
        // Calculate the maximum possible yield from the batch based on the rules.
        double maxWeight = batch.getTotalWeight() * rules.yieldPercentage();
        int maxQty = batch.getCount() * rules.piecesPerWholeChicken();

        // Return a single piece representing the total yield.
        return List.of(new ChickenPiece(rules.pieceName(), maxWeight, maxQty));
    }

    @Override
    public double estimateRequiredChickens(CuttingRules rules, double avgChickenWeight) {
        // This goal is only used when chickens are already provided, so it doesn't need to estimate.
        return 0;
    }

    @Override
    public String getPieceType() {
        return pieceType;
    }
}
