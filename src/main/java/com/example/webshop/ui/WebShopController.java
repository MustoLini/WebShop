package com.example.webshop.ui;

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
        return "redirect:/product";
    }
    @GetMapping("/register")
    public String registerWebsite(Model model){
        return "register";
    }
    @PostMapping ("/register")
    public String register(@RequestParam String loginUser, @RequestParam String password, Model model){
        String checker= websiteService.checkIfUserExist(loginUser, password);
        model.addAttribute("checker", checker);
        return "redirect:/index.html";
    }
}
