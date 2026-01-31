package com.nesa.springboot_rms.itemManagement.domain.model;

/**
 * Holds the biological facts about a specific cut (e.g., Breast vs Wing).
 */
public record CuttingRules(
        double yieldPercentage,
        int piecesPerWholeChicken,
        String pieceName
) {}