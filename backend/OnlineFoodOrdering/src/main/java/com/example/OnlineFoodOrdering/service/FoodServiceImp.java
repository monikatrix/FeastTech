package com.example.OnlineFoodOrdering.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineFoodOrdering.model.Category;
import com.example.OnlineFoodOrdering.model.Food;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.repository.FoodRepository;
import com.example.OnlineFoodOrdering.request.CreateFoodRequest;

@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredient(req.getIngredientItems());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        food.setAvailable(req.isAvailable());
        food.setCreationDate(new Date(0));

        Food savedFood = foodRepository.save(food);
        restaurant.getFoods().add(savedFood);
        return savedFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
    
        // Delete the food from the repository
        foodRepository.delete(food);
        // Food food = findFoodById(foodId);
        // food.setRestaurant(null);
        // foodRepository.save(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal,
            String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId); 

        if(isVegetarian){
            foods = filterByVegetarian(foods, true);
        }

        // if(isVegetarian){
        //     foods = filterByNonVegetarian(foods, isVegetarian==false);
        // }

        if(isNonveg){
            foods = filterByNonVegetarian(foods);
        }

        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }

        if(foodCategory!=null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }
        return foods;
    }
                        
                            
                        
    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonveg) {
        return null;
    }
            
    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return  foods.stream().filter(food->food.isVegetarian()==isVegetarian).collect(Collectors.toList());
                   
    }
    private List<Food> filterByNonVegetarian(List<Food> foods) {
        return  foods.stream()
        .filter(food->!food.isVegetarian())
        .collect(Collectors.toList());
                   
    }
    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return  foods.stream()
        .filter(food->food.isSeasonal()==isSeasonal)
        .collect(Collectors.toList());
                   
    }
    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream()
            .filter(food -> food.getFoodCategory() != null &&
                            food.getFoodCategory().getName().equals(foodCategory))
            .collect(Collectors.toList());
        // return  foods.stream()
        // .filter(food->{
        //     if(food.getFoodCategory()!=null){
        //         return food.getFoodCategory().getName().equals(foodCategory);
        //     }
        //     return false;
        // }).collect(Collectors.toList());
    }
            
    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);

        if(optionalFood.isEmpty()){
            throw new Exception("food not exist...");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}
