package com.example.OnlineFoodOrdering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.request.CreateRestaurantRequest;
import com.example.OnlineFoodOrdering.response.MessageResponse;
import com.example.OnlineFoodOrdering.service.RestaurantService;
import com.example.OnlineFoodOrdering.service.UserService;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {
    
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest res, @RequestHeader("Authorization") String jwt) throws Exception {
            User user = userService.findUserByJwtToken(jwt);

            Restaurant restaurant = restaurantService.createRestaurant(res, user);
            return new ResponseEntity<>(restaurant,HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest res, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
            User user = userService.findUserByJwtToken(jwt);

            Restaurant restaurant = restaurantService.updateRestaurant(id, res);
            return new ResponseEntity<>(restaurant,HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        
        restaurantService.deleteRestaurant(id);
        
        MessageResponse response = new MessageResponse();
        response.setMessage("restaurant deleted successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@RequestBody CreateRestaurantRequest res, @RequestHeader("Authorization") String jwt, @PathVariable Long id) throws Exception {
            User user = userService.findUserByJwtToken(jwt);

            Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
            return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
    
    @GetMapping("/user")
    public ResponseEntity<Restaurant> findRestaurantByUserId( @RequestHeader("Authorization") String jwt) throws Exception {
            User user = userService.findUserByJwtToken(jwt);

            Restaurant restaurant = restaurantService.getRestaurantByUserId(user.getId());
            return new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
}
