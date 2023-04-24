package com.example.webshop.restUI;

import com.example.webshop.business.Product;
import com.example.webshop.business.WebsiteService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestUiController {
    @Autowired
    WebsiteService websiteService;
    @GetMapping("rest/product/all")
    public List<Product> allProduct(){
        return websiteService.getAllProducts();
    }
    @GetMapping("rest/product/id/{id}")
    public Product getProduct(@PathVariable Integer id){
        return websiteService.getByIdProduct(id);
    }
    @GetMapping("rest/product/category/{category}")
    public List<Product> getCategory(@PathVariable String category){
        return websiteService.findBySpecificCategory(category);
    }
    @DeleteMapping("rest/delete/product/{id}")
    public List<Product> deleteProduct(@PathVariable Integer id){
        return websiteService.deleteProduct(id);
    }
    @PostMapping("rest/add/product/{name}/{price}/{category}")
    public List<Product> addProduct(@PathVariable String name, @PathVariable Double price, @PathVariable String category){
        websiteService.addProductIntoDB(name,price,category);
        return websiteService.getAllProducts();
    }
    @PutMapping("rest/update/product/{id}/{price}")
    public List<Product> updateProductPrice(@PathVariable Integer id,@PathVariable Double price){
        websiteService.updatePrice(id,price);
        return websiteService.getAllProducts();
    }


}
