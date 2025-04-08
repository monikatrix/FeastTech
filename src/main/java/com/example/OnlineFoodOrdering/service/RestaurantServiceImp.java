package com.example.OnlineFoodOrdering.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineFoodOrdering.dto.RestaurantDto;
import com.example.OnlineFoodOrdering.model.Address;
import com.example.OnlineFoodOrdering.model.Restaurant;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.repository.AddressRepository;
import com.example.OnlineFoodOrdering.repository.RestaurantRepository;
import com.example.OnlineFoodOrdering.repository.UserRepository;
import com.example.OnlineFoodOrdering.request.CreateRestaurantRequest;

@Service
public class RestaurantServiceImp implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
       Address address = addressRepository.save(req.getAddress());
       

       Restaurant restaurant = new Restaurant();
       restaurant.setAddress(address);
       restaurant.setContactInformation(req.getContactInformation());
       restaurant.setCuisineType(req.getCuisineType());
       restaurant.setDescription(req.getDescription());
       restaurant.setImages(req.getImages());
       restaurant.setName(req.getName());
       restaurant.setOpeningHours(req.getOpeningHours());
       restaurant.setRegistrationDate(LocalDateTime.now());
       restaurant.setOwner(user);

       return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurnat) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurnat.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurnat.getDescription());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(updatedRestaurnat.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
       return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
       return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
       Optional<Restaurant> res = restaurantRepository.findById(id);

       if(res.isEmpty()){
        throw new Exception("restaurant not found with id "+id);
       }
       return res.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant==null){
            throw new Exception("restaurant not found with id "+userId);
        }
        return restaurant; 
    }

    @Override
    public RestaurantDto addToFavourites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto dto = new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);

        if(user.getFavorites().contains(dto)){
            user.getFavorites().remove(dto);
        }
        else{
            user.getFavorites().add(dto);
        }

        userRepository.save(user);
        return dto;

    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
    
}
