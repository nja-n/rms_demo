package com.nesa.springboot_rms.item_demo.purchase.api.dtos;

public record PurchaseRequest(
    String assetId,
    String quantity,
    String unit,
    String vendor,
    String purchaseDate,
    String purchasePrice,
    String purchaseLocation,
    String purchaseCondition,
    String purchaseNotes,
    String purchaseStatus,
    String purchaseType,
    String purchaseSource,
    String purchaseSourceId,
    String purchaseSourceName,
    String purchaseSourceDescription,
    String purchaseSourceLocation,
    String purchaseSourceCondition,
    String purchaseSourceNotes,
    String purchaseSourceStatus,
    String purchaseSourceType
) {
    
}
