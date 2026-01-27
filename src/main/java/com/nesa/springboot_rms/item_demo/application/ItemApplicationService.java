package com.nesa.springboot_rms.item_demo.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nesa.springboot_rms.item_demo.api.dtos.RecipeSplitRuleDto;
import com.nesa.springboot_rms.item_demo.api.dtos.SplitRuleDto;
import com.nesa.springboot_rms.item_demo.domain.Item.Item;

@Service
public class ItemApplicationService {

    List<Item> items = new ArrayList<>();
    HashMap<Long, List<RecipeSplitRuleDto>> ruleMap = new HashMap<>();
    
    public List<Item> getAllItems() {
        // return items;
        return null;
    }

    private List<Item> splitItem(Item item, List<SplitRuleDto> splitRuleDtos) {
        
        for(SplitRuleDto splitRuleDto : splitRuleDtos) {
            Item newItem = new Item();
            // newItem.setId(items.size() + 1L);
            // newItem.setName(splitRuleDto.getName());
            // newItem.setItemCategoryEnum(item.getItemCategoryEnum());
            // newItem.setItemTypeEnum(item.getItemTypeEnum());
            // newItem.setRecipeSplitRuleDto(splitRuleDto);
            items.add(newItem);
        }

        return items;

    }

    public void splitItem(Long id, Long splitRuleId) {
        Item item = items.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
        if(item == null) {
            throw new RuntimeException("Item not found");
        }
        RecipeSplitRuleDto recipeSplitRuleDto = ruleMap.get(id).stream().filter(r -> r.getId() == splitRuleId).findFirst().orElse(null);
        if(recipeSplitRuleDto == null) {
            throw new RuntimeException("Split rule not found");
        }

        List<Item> splittedItems = splitItem(item, recipeSplitRuleDto.getSplitRuleDtos());
        items.addAll(splittedItems);
    }

    public void saveSplitRules(Long id, RecipeSplitRuleDto splitRuleDto) {
        if(ruleMap.containsKey(id)) {
            ruleMap.get(id).add(splitRuleDto);
        } else {
            ruleMap.put(id, new ArrayList<>());
            ruleMap.get(id).add(splitRuleDto);
        }
    }


}
