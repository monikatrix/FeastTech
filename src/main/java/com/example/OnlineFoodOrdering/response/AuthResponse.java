package com.example.OnlineFoodOrdering.response;

import com.example.OnlineFoodOrdering.model.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    
    private String message;
    private USER_ROLE role;
}
