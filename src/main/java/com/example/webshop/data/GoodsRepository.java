package com.example.webshop.data;

import com.example.webshop.business.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Product,Long> {

}
