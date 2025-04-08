package com.example.OnlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OnlineFoodOrdering.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
    public User findByEmail(String username);

    
}
