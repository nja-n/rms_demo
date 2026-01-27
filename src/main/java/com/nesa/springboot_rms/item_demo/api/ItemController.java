package com.nesa.springboot_rms.item_demo.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nesa.springboot_rms.item_demo.api.dtos.RecipeSplitRuleDto;
import com.nesa.springboot_rms.item_demo.application.ItemApplicationService;
import com.nesa.springboot_rms.item_demo.application.processing_domain_service.ProcessedResult;
import com.nesa.springboot_rms.item_demo.application.processing_domain_service.ProcessingMachine;
import com.nesa.springboot_rms.item_demo.domain.Item.Item;
import com.nesa.springboot_rms.item_demo.domain.Item.inventory_context.RawIngredient;
import com.nesa.springboot_rms.item_demo.domain.prep_aggregate.CuttingTemplate;
import com.nesa.springboot_rms.item_demo.domain.prep_aggregate.OutputRule;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemApplicationService itemApplicationService;
    private final ProcessingMachine processingMachine;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemApplicationService.getAllItems());
    }

    @PostMapping("/split")
    public String postSplit(@RequestParam Long id, @RequestParam Long splitRuleId) {
        itemApplicationService.splitItem(id, splitRuleId);
        return "";
    }

    @PostMapping("/split-rules")
    public String postSplitRule(@RequestParam Long id, @RequestBody RecipeSplitRuleDto splitRuleDto) {
        itemApplicationService.saveSplitRules(id, splitRuleDto);
        return "";
    }

    @GetMapping("/demo-process")
    public ProcessedResult runDemoProcess() {
        // 1. Create a Raw Ingredient (e.g., 10kg Whole Salmon)
        RawIngredient salmon = new RawIngredient(101L, "Whole Salmon", 10.0);

        // 2. Define the Cutting Template
        OutputRule ruleFillet = new OutputRule(1L, "Fillet", 60.0, false);
        OutputRule ruleBones = new OutputRule(2L, "Bones/Head", 30.0, true);
        OutputRule ruleSkin = new OutputRule(3L, "Skin", 10.0, true);

        CuttingTemplate salmonTemplate = new CuttingTemplate(
                500L,
                "Standard Salmon Breakdown",
                Arrays.asList(ruleFillet, ruleBones, ruleSkin));

        // 3. Process
        return processingMachine.process(salmon, salmonTemplate);
    }

    @GetMapping("/demo-alpham")
    public ProcessedResult runDemoAlpham() {
        // 1. Create a Raw Ingredient (e.g., 10kg Whole Salmon)
        RawIngredient salmon = new RawIngredient(101L, "Chicken", 10.0);

        // 2. Define the Cutting Template
        OutputRule ruleBreast = new OutputRule(1L, "Breast", 30.0, false);
        OutputRule ruleThigh = new OutputRule(2L, "Thigh", 30.0, false);
        OutputRule ruleLeg = new OutputRule(3L, "Leg", 25.0, false);
        OutputRule ruleWing = new OutputRule(4L, "Wing", 15.0, true);

        CuttingTemplate salmonTemplate = new CuttingTemplate(
                500L,
                "Standard Salmon Breakdown",
                Arrays.asList(ruleBreast, ruleThigh, ruleLeg, ruleWing));

        // 3. Process
        return processingMachine.process(salmon, salmonTemplate);
    }
}
