package com.example.OnlineFoodOrdering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OnlineFoodOrdering.model.Cart;
import com.example.OnlineFoodOrdering.model.CartItems;
import com.example.OnlineFoodOrdering.model.Food;
import com.example.OnlineFoodOrdering.model.User;
import com.example.OnlineFoodOrdering.repository.CartItemRepository;
import com.example.OnlineFoodOrdering.repository.CartRepository;
import com.example.OnlineFoodOrdering.request.AddCartItemRequest;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private FoodService foodService;

    @Override
    public CartItems addItemtoCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItems cartItem : cart.getItems()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity()+req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItems newCartItem = new CartItems();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        // int totalPrice = req.getQuantity()*food.getPrice();
        newCartItem.setTotalPrice((int) (req.getQuantity()*food.getPrice()));

        CartItems savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItems updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItems> cartItems = cartItemRepository.findById(cartItemId);

        if(cartItems.isEmpty()){
            throw new Exception("cart item is not found");
        }
        CartItems item = cartItems.get();
        item.setQuantity(quantity);
        item.setTotalPrice((int) (item.getFood().getPrice()*quantity));

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartRepository.findByCustomerId(user.getId());
        Optional<CartItems> cartItemOptional = cartItemRepository.findById(cartItemId);

        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item is not found");
        }
        CartItems items = cartItemOptional.get();

        cart.getItems().remove(items);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
       Long total = 0L;

       for(CartItems cartItem:cart.getItems()){
        total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
       }
       return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalcart = cartRepository.findById(id);
        if(optionalcart.isEmpty()){
            throw new Exception("cart not found with id "+id);
        }
        return optionalcart.get();
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        // User user = userService.findUserByJwtToken(jwt);
        Cart cart = findCartById(userId);

        cart.getItems().clear();
        return cartRepository.save(cart);
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {

       Cart cart = cartRepository.findByCustomerId(userId);

       cart.setTotal(calculateCartTotals(cart));
       return cart;
    }

    
}
