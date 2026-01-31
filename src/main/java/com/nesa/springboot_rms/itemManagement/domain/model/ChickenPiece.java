package com.nesa.springboot_rms.itemManagement.domain.model;

/**
 * Value Object representing the result of the cutting process.
 */
public record ChickenPiece(String type, double weightInGrams, int quantity) {}
