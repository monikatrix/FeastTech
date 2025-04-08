package com.example.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OnlineFoodOrdering.model.CartItems;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems,Long> {
    // public Cart findByCustomerId(Long userId);
}
