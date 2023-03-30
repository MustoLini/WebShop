package com.example.webshop.ui;

import com.example.webshop.business.Cart;
import com.example.webshop.business.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class WebShopController {
    @Autowired
    WebsiteService websiteService;

    @PostMapping("/login")
    public String login(@RequestParam String loginUser, @RequestParam String password, Model model){
        model.addAttribute("person", websiteService.login(loginUser,password));
        model.addAttribute("products", websiteService.getAllProducts());
        return "productShop";
    }
    @GetMapping("/register")
    public String registerWebsite(Model model){
        return "register";
    }
    @PostMapping ("/register")
    public String register(@RequestParam String loginUser, @RequestParam String password, Model model){
        String checker= websiteService.checkIfUserExist(loginUser, password);
        model.addAttribute("checker", checker);
        return "register";
    }
    @GetMapping("/addingProducts")
    public String addProductWebsite(){
        return "addingProducts";
    }
    @PostMapping("/addingProducts")
    public String addProduct(@RequestParam String productName, @RequestParam Double price, @RequestParam String category, Model model){
        model.addAttribute("product", websiteService.addProductIntoDB(productName,price,category));
        return "addingProducts";
    }
    @PostMapping ("/addintocart")
    public String addIntoCart(@RequestParam Long index, @RequestParam int amount, Model model){
        Cart cart= websiteService.addProductIntoCart(index,amount);
        model.addAttribute("products", websiteService.getAllProducts());
        System.out.println(index +" CArt " + amount);
        model.addAttribute("cart",cart);
        return "productShop";
    }
    /*@GetMapping("/product")
    public String buyProduct(@RequestParam long id,@RequestParam int amount, Model model){
        Cart cart= websiteService.addProductIntoCart(id,amount);
        model.addAttribute("cart",cart);
        return "showCart";
    }*/
    @GetMapping("/showCart")
    public String showCart(Model model){
        model.addAttribute("cart",websiteService.getCart());
        return "showCart";
    }



}
