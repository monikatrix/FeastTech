package com.example.OnlineFoodOrdering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineFoodOrdering.model.Cart;
import com.example.OnlineFoodOrdering.model.CartItems;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.request.AddCartItemRequest;
import com.example.OnlineFoodOrdering.request.UpdateCartItemRequest;
import com.example.OnlineFoodOrdering.service.CartService;
import com.example.OnlineFoodOrdering.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItems> addItemToCart(@RequestBody AddCartItemRequest req,
                                                    @RequestHeader("Authorization") String jwt) throws Exception{
        CartItems cartItem = cartService.addItemtoCart(req, jwt);
        return new ResponseEntity<>(cartItem,HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItems> updatecartItemQuantity(@RequestBody UpdateCartItemRequest req,
                                                    @RequestHeader("Authorization") String jwt) throws Exception{
        CartItems cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return new ResponseEntity<>(cartItem,HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removecartItem(@PathVariable Long id,
                                                @RequestHeader("Authorization") String jwt) throws Exception{
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @PutMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }
    
    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

}
