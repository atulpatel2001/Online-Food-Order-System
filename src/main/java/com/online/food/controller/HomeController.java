package com.online.food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping("/signin")
    public String signin(Model model){
        model.addAttribute("title","Customer | Login-Page");
        return "signin";
    }
}
