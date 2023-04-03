package com.example.webshop.business;

import java.util.ArrayList;
import java.util.List;


public class Cart {

    List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public List<CartItem> removeItemFromCart(Integer id) {
        List<CartItem> cartItemCart = getCartItems();
        if (cartItemCart.get(id).amount == 0) {
            cartItemCart.remove(id);
            return cartItemCart;
        } else {
            cartItemCart.get(id).removeOneFromAmount();
            return cartItemCart;
        }

    }

}
