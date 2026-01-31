package com.nesa.springboot_rms.itemManagement.application.dto;

import com.nesa.springboot_rms.itemManagement.domain.model.CuttingGoal;

import java.util.List;

public record OrderRequest(
        List<Double> chickenWeights,
        List<CuttingGoal> goals
) {}
