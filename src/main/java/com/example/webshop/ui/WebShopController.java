package com.example.webshop.ui;

import com.example.webshop.business.Product;
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
        return "redirect:/productShop";
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
        model.addAttribute("product", websiteService.addProduct(productName,price,category));
        return "addingProducts";
    }
    @PostMapping ("/productShop")
    public String showAllProducts(Model model){
        model.addAttribute("productShop",websiteService.getAllProducts());
        return "productShop";
    }
    @GetMapping("/product")
    public String buyProduct(@RequestParam long id, Model model){
        Product product=websiteService.getByIdProduct(id);
        model.addAttribute("product",product);
        return "/";
    }


}
