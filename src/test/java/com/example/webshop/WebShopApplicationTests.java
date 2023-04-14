package com.example.webshop;

import com.example.webshop.business.Product;
import com.example.webshop.business.WebsiteService;
import com.example.webshop.data.OrderRepository;
import com.example.webshop.data.PersonRepository;
import com.example.webshop.data.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WebShopApplicationTests {
    @Autowired
    WebsiteService websiteService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    PersonRepository personRepository;
    Product product = Mockito.mock(Product.class);
    Product product2 = Mockito.mock(Product.class);
    Product product3 = Mockito.mock(Product.class);
    Product product4 = Mockito.mock(Product.class);

    @BeforeEach
    void setUp() {
        websiteService.addProductIntoDB(product.getName(), product.getPrice(), product.getCategory());
        websiteService.addProductIntoDB(product2.getName(), product2.getPrice(), product2.getCategory());
        websiteService.addProductIntoDB(product3.getName(), product3.getPrice(), product3.getCategory());
    }

    @Test
    void addIntoDBTest() {
        websiteService.addProductIntoDB(product4.getName(), product4.getPrice(), product4.getCategory());
        assertEquals(4, productRepository.findAll().size());
    }

    @Test
    void addingIntoCartTest() {
        websiteService.addProductIntoCart(1L, 2);
        websiteService.addProductIntoCart(2L, 4);
        websiteService.addProductIntoCart(3L, 2);
        assertEquals(3, websiteService.getCart().getCartItems().size());
    }

}
