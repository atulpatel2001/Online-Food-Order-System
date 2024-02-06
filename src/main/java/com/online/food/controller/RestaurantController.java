package com.online.food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    @GetMapping("/index")
    public String indexPage(Model model){
        model.addAttribute("title","Restaurant | Dash-Board");
        return "restaurant/index";
    }
}
