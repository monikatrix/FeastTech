package com.example.OnlineFoodOrdering.service;

import java.util.List;

import com.example.OnlineFoodOrdering.model.Category;

public interface CategoryService {
    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> findCategoriesByRestaurantId(Long id) throws Exception;
    
    public Category findCategoriesById(Long id) throws Exception;

}
