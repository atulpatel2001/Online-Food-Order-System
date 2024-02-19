package com.online.food.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurant-staff")
public class StaffController {


    private Logger logger= LoggerFactory.getLogger(RestaurantController.class);


    //staff dash board
    @GetMapping("/index")
    public String indexPage(Model model){
        model.addAttribute("title","STAFF | Dash-Board");
        return "staff/index";
    }


}
