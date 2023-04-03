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

    public List<CartItem> removeItemFromCart(int id) {
        if (getCartItems().get(id).getAmount() == 1) {
            getCartItems().remove(id);
            return getCartItems();
        } else {
            getCartItems().get(id).removeOneFromAmount();
            return getCartItems();
        }

    }

}
