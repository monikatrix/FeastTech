package com.example.OnlineFoodOrdering.request;

import java.util.List;

import com.example.OnlineFoodOrdering.model.Category;
import com.example.OnlineFoodOrdering.model.IngredientItems;

import lombok.Data;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private  List<String> images;

    private boolean available;
    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientItems> ingredientItems;
   

}
