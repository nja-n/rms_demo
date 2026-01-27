package com.nesa.springboot_rms.item_demo.api.dtos;

public record SplitRuleDto(
    Long id,
    String name,
    String description,
    String type,
    String unit,
    String quantity,
    String price,
    Double percentage
) {
    public Long getId() {
        return id;
    }
    public SplitRuleDto setId(Long id) {
        return new SplitRuleDto(id, 
            name,
            description,
            type,
            unit,
            quantity,
            price,
            percentage
        );
    }
}
