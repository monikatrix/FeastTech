package com.example.OnlineFoodOrdering.service;

import java.util.List;

import com.example.OnlineFoodOrdering.model.IngredientCategory;
import com.example.OnlineFoodOrdering.model.IngredientItems;

public interface IngredientsService {
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientItems createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientItems> findRestaurantsIngredients(Long restaurantId);

    public IngredientItems updateStock(Long id) throws Exception;
}
