package com.example.OnlineFoodOrdering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OnlineFoodOrdering.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    public List<Order> findByCustomerId(Long id);

    public List<Order> findByRestaurantId(Long restaurantId);
}
