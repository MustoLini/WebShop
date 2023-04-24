package com.example.webshop;

import com.example.webshop.business.Product;
import com.example.webshop.business.WebsiteService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CartClassTests {
    @Autowired
    WebsiteService websiteService;
    Product product = new Product("Bulle",4.0, "Bakverk");
    Product product2 = new Product("Pc", 10000.0, "Dator");
    Product product3 = new Product("HårdDisk",500.0,"HårdWarda");

    @BeforeEach
    void setUp() {
        websiteService.addProductIntoDB(product.getName(), product.getPrice(), product.getCategory());
        websiteService.addProductIntoDB(product2.getName(), product2.getPrice(), product2.getCategory());
        websiteService.addProductIntoDB(product3.getName(), product3.getPrice(), product3.getCategory());
        websiteService.addProductIntoCart(1L, 2);
        websiteService.addProductIntoCart(2L, 4);
        websiteService.addProductIntoCart(3L, 2);
    }

    @Test
    void addingIntoCartTest() {
        assertEquals(3, websiteService.getCart().getCartItems().size());
    }

    @Test
    void removeItemFromCartTest() {
        websiteService.getCart().removeItemFromCart(0);
        assertEquals(1, websiteService.getCart().getCartItems().get(0).getAmount());
    }

    @Test
    void clearCartTest() {
        websiteService.clearCart();
        assertEquals(0, websiteService.getCart().getCartItems().size());
    }
    @Test
    void sumCartTest(){
        assertEquals(41008.0,websiteService.getCart().sumOfAllProducts());
    }


}
