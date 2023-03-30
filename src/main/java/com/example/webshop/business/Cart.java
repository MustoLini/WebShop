package com.example.webshop.business;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


public class Cart {

    List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

     public List<CartItem> getCartItems(){return cartItems;}
}
