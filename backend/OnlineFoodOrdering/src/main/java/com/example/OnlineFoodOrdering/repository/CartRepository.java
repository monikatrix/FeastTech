package com.example.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineFoodOrdering.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{
    public Cart findByCustomerId(Long userId);
} 
