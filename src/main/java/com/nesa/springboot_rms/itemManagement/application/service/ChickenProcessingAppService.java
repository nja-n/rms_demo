package com.nesa.springboot_rms.itemManagement.application.service;

import com.nesa.springboot_rms.itemManagement.domain.model.ChickenBatch;
import com.nesa.springboot_rms.itemManagement.domain.model.ChickenPiece;
import com.nesa.springboot_rms.itemManagement.domain.model.CuttingGoal;
import com.nesa.springboot_rms.itemManagement.domain.model.WholeChicken;
import com.nesa.springboot_rms.itemManagement.domain.service.ChickenCuttingDomainService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChickenProcessingAppService {

    private static final double DEFAULT_CHICKEN_WEIGHT_GRAMS = 1200.0;
    private final ChickenCuttingDomainService domainService;
    // private final InventoryRepository inventoryRepo; // Uncomment when ready for DB

    // Spring injects the DomainService (which was configured in StrategyConfig.java)
    public ChickenProcessingAppService(ChickenCuttingDomainService domainService) {
        this.domainService = domainService;
    }

    @Transactional
    public List<ChickenPiece> executeKitchenOrder(List<Double> chickenWeights, List<? extends CuttingGoal> goals) {
        List<Double> finalWeights = chickenWeights;
        // Ensure goals is not null to avoid NPE
        List<? extends CuttingGoal> finalGoals = (goals != null) ? goals : new ArrayList<>();


        if (finalWeights == null || finalWeights.isEmpty()) {
            if (finalGoals.isEmpty()) {
                // If both are missing/empty, we can't do anything.
                // But if goals is empty and weights are provided, we just cut weights (which yields nothing specific but valid).
                // Here weights are missing, so we MUST have goals.
                throw new IllegalArgumentException("Cannot process order without chickens or goals.");
            }
            // Calculate how many chickens we need based on the goals
            int requiredCount = domainService.calculateBatchSize((List<CuttingGoal>) finalGoals, DEFAULT_CHICKEN_WEIGHT_GRAMS);
            
            finalWeights = new ArrayList<>();
            for (int i = 0; i < requiredCount; i++) {
                finalWeights.add(DEFAULT_CHICKEN_WEIGHT_GRAMS);
            }
        }

        // 2. Convert Primitives to Domain Aggregate (Batching)
        List<WholeChicken> chickens = finalWeights.stream()
                .map(WholeChicken::new)
                .collect(Collectors.toList());

        ChickenBatch batch = new ChickenBatch(chickens);

        // 3. Delegate to Domain Core
        // Notice: The App Service doesn't know about "Weight vs Qty". It just passes the objects.
        List<ChickenPiece> result = domainService.processRequest(batch, (List<CuttingGoal>) finalGoals);

        // 4. Persistence (Infrastructure concern)
        // inventoryRepo.deleteAll(chickens); // Consume raw inventory
        // inventoryRepo.saveAll(result);     // Add new cut pieces

        return result;
    }
}
