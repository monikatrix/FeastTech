package com.example.OnlineFoodOrdering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineFoodOrdering.model.Food;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.request.CreateFoodRequest;
import com.example.OnlineFoodOrdering.response.MessageResponse;
import com.example.OnlineFoodOrdering.service.FoodService;
import com.example.OnlineFoodOrdering.service.RestaurantService;
import com.example.OnlineFoodOrdering.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodService foodService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
        Food food = foodService.createFood(req, req.getCategory(), restaurant);

        return new ResponseEntity<>(food,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception{
        try {
            User user = userService.findUserByJwtToken(jwt);
            foodService.deleteFood(id);
    
            MessageResponse res = new MessageResponse();
            res.setMessage("Food deleted successfully");
    
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            MessageResponse res = new MessageResponse();
            res.setMessage(e.getMessage());
    
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        // User user = userService.findUserByJwtToken(jwt);
        
        // foodService.findFoodById(id);
        // foodService.deleteFood(id); 

        // MessageResponse res = new MessageResponse();
        // res.setMessage("food deleted successfully");

        // return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        
        Food food = foodService.updateAvailabilityStatus(id);

    
        return new ResponseEntity<>(food,HttpStatus.OK);
    }
}
