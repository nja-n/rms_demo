package com.nesa.springboot_rms.kitchen.application;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nesa.springboot_rms.kitchen.domain.Ingredient;
import com.nesa.springboot_rms.kitchen.domain.Meal;
import com.nesa.springboot_rms.kitchen.domain.StockIngredient;
import com.nesa.springboot_rms.kitchen.domain.repo.IngredientRepository;
import com.nesa.springboot_rms.kitchen.domain.repo.RecipeRepository;
import com.nesa.springboot_rms.outlet.domain.extention.OutletId;

/**
 * @author vishnu mg
 * @version 1.0
 * @since 2026-01-16
 * @see KitchenApplicationService
 * @Company Nesa
 */
@Service
public class KitchenApplicationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public KitchenApplicationService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<StockIngredient> getAllStockIngredientsByOutletId(OutletId outletId) {
        List<StockIngredient> existingStock = ingredientRepository.findCurrentStockByOutletId(outletId);
        List<Ingredient> allIngredients = ingredientRepository.getAllByOutletId(outletId);

        // 2. Map existing stock by Ingredient ID for O(1) lookup
        Map<Long, StockIngredient> stockMap = existingStock.stream()
                .collect(Collectors.toMap(
                        s -> s.getIngredient().getId(),
                        s -> s,
                        (existing, replacement) -> existing // Handle duplicates if any
                ));

        // 3. Ensure every ingredient exists in the result
        return allIngredients.stream().map(ingredient -> {
            if (stockMap.containsKey(ingredient.getId())) {
                // Ingredient exists in stock, return it
                StockIngredient stock = stockMap.get(ingredient.getId());

                // stock.setIngredient(ingredient);
                return stock;
            } else {
                // Create a "Virtual" Stock entry with 0 qty
                StockIngredient zeroStock = StockIngredient.builder()
                    .ingredient(ingredient)
                    .quantity(0.0)
                    .outletId(outletId)
                    .build();
                return zeroStock;
            }
        }).collect(Collectors.toList());
    }

    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.getByName(name);
    }

    public List<Meal> getMeals(String id) {
        return recipeRepository.getMeals(id);
    }

    public StockIngredient getIngredientByStockId(Long id) {
        return ingredientRepository.getById(id);
    }

}

// @Data
// class BoundedList {
//     private List<MealDto> meals;
//     public List<Ingredient> sync() {

//         String syncServiceUrl = "https://www.themealdb.com/api/json/v1/1/search.php?f=a";
//         BoundedList response = restTemplate.getForObject(syncServiceUrl, BoundedList.class);

//         for (MealDto mealDto : response.getMeals()) {
//             // 1️⃣ Save meal
//             Meal meal = Meal.builder()
//                     .id(null)
//                     .name(mealDto.getStrMeal())
//                     .category(mealDto.getStrCategory())
//                     .area(mealDto.getStrArea())
//                     .instructions(mealDto.getStrInstructions())
//                     .thumbnail(mealDto.getStrMealThumb())
//                     .youtube(mealDto.getStrYoutube())
//                     .source(mealDto.getStrSource())
//                     .build();

//             // 2️⃣ Loop ingredients
//             for (int i = 1; i <= 20; i++) {
//                 String ingredientName = (String) MealDtoUtils.getIngredient(mealDto, i);
//                 String measure = (String) MealDtoUtils.getMeasure(mealDto, i);

//                 if (ingredientName == null || ingredientName.isBlank())
//                     continue;

//                 Ingredient ingredient = getIngredientByName(ingredientName);

//                 MealIngredient mi = MealIngredient.builder()
//                         // .meal(meal)
//                         .ingredient(ingredient)
//                         .measure(measure)
//                         .id(null)
//                         .build();
//                 meal.addMealIngredient(mi);
//             }

//             recipeRepository.saveMeal(meal);

//         }

//         return new ArrayList<Ingredient>();
//     }

// }
