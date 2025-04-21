package com.example.OnlineFoodOrdering.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineFoodOrdering.model.IngredientCategory;
import com.example.OnlineFoodOrdering.model.IngredientItems;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.repository.IngredientCategoryRepository;
import com.example.OnlineFoodOrdering.repository.IngredientItemRepository;

@Service
public class IngredientServiceImp implements IngredientsService {

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;
    
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientItems createIngredientItem(Long restaurantId, String ingredientName, Long categoryId)
            throws Exception {
                Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
                IngredientCategory category = findIngredientCategoryById(categoryId);

                IngredientItems ingredientItems = new IngredientItems();
                ingredientItems.setName(ingredientName);
                ingredientItems.setRestaurant(restaurant);
                ingredientItems.setCategory(category);

                IngredientItems ingredient = ingredientItemRepository.save(ingredientItems);

                return ingredient;
    }

    @Override
    public List<IngredientItems> findRestaurantsIngredients(Long restaurantId) {

        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientItems updateStock(Long id) throws Exception {
        Optional<IngredientItems> optional = ingredientItemRepository.findById(id);
        if(optional.isEmpty()){
            throw new Exception("ingredient not found!");
        }
        IngredientItems item = optional.get();
        item.setInStoke(!item.isInStoke());
        return ingredientItemRepository.save(item);
    }
    
}
