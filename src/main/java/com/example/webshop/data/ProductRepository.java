package com.example.webshop.data;

import com.example.webshop.business.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByName(String name);

    List<Product> findByCategory(String category);



}
