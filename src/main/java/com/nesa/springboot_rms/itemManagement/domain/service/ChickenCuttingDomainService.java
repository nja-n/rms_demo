package com.nesa.springboot_rms.itemManagement.domain.service;

import com.nesa.springboot_rms.itemManagement.domain.model.ChickenBatch;
import com.nesa.springboot_rms.itemManagement.domain.model.ChickenPiece;
import com.nesa.springboot_rms.itemManagement.domain.model.CuttingGoal;
import com.nesa.springboot_rms.itemManagement.domain.model.MaxYieldGoal;
import com.nesa.springboot_rms.itemManagement.domain.strategy.ChickenCuttingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChickenCuttingDomainService {

    private final Map<String, ChickenCuttingStrategy> strategies;

    // Constructor Injection (Pure Java)
    public ChickenCuttingDomainService(List<ChickenCuttingStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(s -> s.getStrategyName().toUpperCase(), s -> s));
    }

    public List<ChickenPiece> processRequest(ChickenBatch batch, List<CuttingGoal> goals) {
        List<ChickenPiece> totalResults = new ArrayList<>();
        List<CuttingGoal> goalsToProcess = new ArrayList<>(goals);

        // If no goals provided, assume "Cut Everything"
        if (goalsToProcess.isEmpty()) {
            for (String strategyName : strategies.keySet()) {

                goalsToProcess.add(new MaxYieldGoal(strategyName));
            }
        }

        for (CuttingGoal goal : goalsToProcess) {
            // 1. Polymorphic Lookup (Replaces switch-case)
            ChickenCuttingStrategy strategy = Optional.ofNullable
                            (strategies.get(goal.getPieceType().toUpperCase()))
                    .orElseThrow(() -> new IllegalArgumentException
                            ("No strategy found for type: " + goal.getPieceType()));

            // 2. Execute Strategy (Replaces logic branching)
            // The strategy will internally call goal.calculate() to handle Weight vs Qty
            List<ChickenPiece> pieces = strategy.cut(batch, goal);

            totalResults.addAll(pieces);
        }

        // (Optional: You could add logic here to check if totalResults exceed batch weight)

        return totalResults;
    }

    public int calculateBatchSize(List<CuttingGoal> goals, double avgChickenWeight) {
        double maxChickens = 0;
        for (CuttingGoal goal : goals) {
            ChickenCuttingStrategy strategy = Optional.ofNullable
                            (strategies.get(goal.getPieceType().toUpperCase()))
                    .orElseThrow(() -> new IllegalArgumentException
                            ("No strategy found for type: " + goal.getPieceType()));

            double required = strategy.calculateRequiredChickens(goal, avgChickenWeight);
            if (required > maxChickens) {
                maxChickens = required;
            }
        }
        return (int) Math.ceil(maxChickens);
    }
}
