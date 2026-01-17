package com.nesa.springboot_rms.kitchen.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesa.springboot_rms.kitchen.application.KitchenApplicationService;
import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.domain.Meal;
import com.nesa.springboot_rms.kitchen.domain.StockIngredient;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/kitchen")
@RequiredArgsConstructor
public class KitchenController {
    private final KitchenApplicationService kitchenApplicationService;

    @GetMapping("/sync")
    public ResponseEntity<String> sync() {
        return ResponseEntity.ok("Syncing");
        // return ResponseEntity.ok(kitchenApplicationService.sync());
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<StockIngredient>> getAllIngredients() {
        OutletId outletId = new OutletId(1L);
        return ResponseEntity.ok(kitchenApplicationService.getAllStockIngredientsByOutletId(outletId));
    }
    
    @GetMapping("/ingredients/{id}")
    public ResponseEntity<StockIngredient> getIngredientById(@PathVariable Long id) {
        return ResponseEntity.ok(kitchenApplicationService.getIngredientByStockId(id));
    }

    @GetMapping("/meals")
    public ResponseEntity<List<Meal>> getMeals() {
        return ResponseEntity.ok(kitchenApplicationService.getMeals(null));
    }

    @GetMapping("/meals/{id}")
    public ResponseEntity<Meal> getMealById(@PathVariable String id) {
        List<Meal> meals = kitchenApplicationService.getMeals(id);
        if(meals.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(meals.get(0));
        }
    }
    
}
