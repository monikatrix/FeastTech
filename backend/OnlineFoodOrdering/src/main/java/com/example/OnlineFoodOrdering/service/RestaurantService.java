package com.example.OnlineFoodOrdering.service;

import java.util.List;

import com.example.OnlineFoodOrdering.dto.RestaurantDto;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.request.CreateRestaurantRequest;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) throws Exception;

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurnat) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public User addToFavorites(Long restaurantId, String jwt) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
