package com.nesa.springboot_rms.itemManagement.domain.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuantityBasedGoal.class, name = "QTY"),
        @JsonSubTypes.Type(value = WeightBasedGoal.class, name = "WEIGHT")
})
public interface CuttingGoal {

    // The polymorphic method that replaces if-else
    List<ChickenPiece> calculate(ChickenBatch batch, CuttingRules rules);

    /**
     * Estimates the number of whole chickens needed to satisfy this goal.
     */
    double estimateRequiredChickens(CuttingRules rules, double avgChickenWeight);

    // Used for Map lookup
    String getPieceType();

}
