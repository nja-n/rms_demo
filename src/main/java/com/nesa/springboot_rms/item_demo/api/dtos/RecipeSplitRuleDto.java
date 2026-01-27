package com.nesa.springboot_rms.item_demo.api.dtos;

import java.util.List;

public record RecipeSplitRuleDto(
    Long id,
    String name,
    List<SplitRuleDto> splitRuleDtos
) {
    public Long getId() {
        return id;
    }

    public List<SplitRuleDto> getSplitRuleDtos() {
        return splitRuleDtos;
    }
}
