package com.online.food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/index")
    public String indexPage(Model model){
        try{
            model.addAttribute("title", "Admin | DashBoard");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "admin/index";
    }
}
