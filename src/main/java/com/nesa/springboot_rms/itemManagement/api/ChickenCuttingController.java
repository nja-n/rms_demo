package com.nesa.springboot_rms.itemManagement.api;


import com.nesa.springboot_rms.itemManagement.application.dto.OrderRequest;
import com.nesa.springboot_rms.itemManagement.application.service.ChickenProcessingAppService;
import com.nesa.springboot_rms.itemManagement.domain.model.ChickenPiece;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item/chicken")
public class ChickenCuttingController {

    private final ChickenProcessingAppService appService;

    public ChickenCuttingController(ChickenProcessingAppService appService) {
        this.appService = appService;
    }

    @PostMapping("/cut")
    public ResponseEntity<List<ChickenPiece>> cutChicken(@RequestBody OrderRequest request) {
        // Validation: At least one of 'chickenWeights' OR 'goals' must be present.
        boolean hasChickens = request.chickenWeights() != null && !request.chickenWeights().isEmpty();
        boolean hasGoals = request.goals() != null && !request.goals().isEmpty();

        if (!hasChickens && !hasGoals) {
            return ResponseEntity.badRequest().build();
        }

        List<ChickenPiece> results = appService.executeKitchenOrder(
                request.chickenWeights(),
                request.goals()
        );

        return ResponseEntity.ok(results);
    }
}
