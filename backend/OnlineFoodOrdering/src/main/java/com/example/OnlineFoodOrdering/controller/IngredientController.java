package com.example.OnlineFoodOrdering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineFoodOrdering.model.IngredientCategory;
import com.example.OnlineFoodOrdering.model.IngredientItems;
import com.example.OnlineFoodOrdering.request.IngredientCategoryRequest;
import com.example.OnlineFoodOrdering.request.IngredientRequest;
import com.example.OnlineFoodOrdering.service.IngredientsService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
        @RequestBody IngredientCategoryRequest req
    ) throws Exception{
        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(),req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    
    @PostMapping()
    public ResponseEntity<IngredientItems> createIngredientItem(
        @RequestBody IngredientRequest req
    ) throws Exception{
        IngredientItems item = ingredientsService.createIngredientItem(req.getRestaurantId(),req.getName(),req.getCategoryId()
    );
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientItems> updateIngredientStock(
       @PathVariable Long id
    ) throws Exception{
        IngredientItems item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientItems>> getRestaurantIngredients(
       @PathVariable Long id
    ) throws Exception{
        List<IngredientItems> items = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    
    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
       @PathVariable Long id
    ) throws Exception{
        List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
