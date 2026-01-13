package com.nesa.springboot_rms.outlet.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesa.springboot_rms.outlet.application.RecipeApplicationService;
import com.nesa.springboot_rms.outlet.domain.Recipe;
import com.nesa.springboot_rms.outlet.domain.enums.CommonStatus;
import com.nesa.springboot_rms.outlet.api.OutletController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeApplicationService recipeService;

    private final OutletController outletController;
    
    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes(outletController.hardCodedOutletId));
    }

    @PostMapping
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe) {
        recipe = recipeService.createRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }
    
}
