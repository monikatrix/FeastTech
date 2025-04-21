package com.example.OnlineFoodOrdering.service;

import com.example.OnlineFoodOrdering.model.Cart;
import com.example.OnlineFoodOrdering.model.CartItems;
import com.example.OnlineFoodOrdering.request.AddCartItemRequest;

public interface CartService {
    public CartItems addItemtoCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItems updateCartItemQuantity(Long cartItemId, int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart clearCart(Long userId) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

}
