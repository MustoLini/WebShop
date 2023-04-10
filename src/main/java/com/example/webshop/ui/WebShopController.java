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
        model.addAttribute("category", websiteService.getAllCategories());
        return "productShop";
    }
    @GetMapping("/register")
    public String registerWebsite(Model model){
        return "register";
    }
    @PostMapping ("/register")
    public String register(@RequestParam String loginUser, @RequestParam String password,@RequestParam(value = "adminBox", required = false) Boolean adminBox, Model model){
        if (adminBox== null){
            adminBox =false;
        }
        String checker= websiteService.checkIfUserExist(loginUser, password, adminBox);
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
        model.addAttribute("category", websiteService.getAllCategories());
        System.out.println(index +" CArt " + amount);
        model.addAttribute("cart",cart);
        return "productShop";
    }

    @GetMapping("/showCart")
    public String showCart(Model model){
        model.addAttribute("cart",websiteService.getCart());
        model.addAttribute("amount", websiteService.getCart().sumOfAllProducts());
        return "showCart";
    }
    @PostMapping("/removeproductfromcart")
    public String removeFromCart(Model model, @RequestParam Integer id){
        websiteService.removeCartItem(id);
        model.addAttribute("amount", websiteService.getCart().sumOfAllProducts());
        model.addAttribute("cart",websiteService.getCart());
        return "showcart";
    }
    @PostMapping("/placeorder")
    public String placeOrder(Model model){
        websiteService.addIntoOrder();
        model.addAttribute("customersorders", websiteService.getCustomerOrders());
        return "orderPlaced";
    }
    @PostMapping("/showspecifiedproduct")
    public String searchForSpecifiedProduct(Model model, @RequestParam String specifiedProduct){
        model.addAttribute("products", websiteService.findSpecificProduct(specifiedProduct));
        model.addAttribute("category", websiteService.getAllCategories());
        return "productShop";
    }
    @PostMapping("/showspecifiedcategory")
    public String searchForSpecifiedCategory(Model model,@RequestParam String chosencategory){
        model.addAttribute("category", websiteService.getAllCategories());
        model.addAttribute("products", websiteService.findBySpecificCategory(chosencategory));
        return "productShop";
    }



}
