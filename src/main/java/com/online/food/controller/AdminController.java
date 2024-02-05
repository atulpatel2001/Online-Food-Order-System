package com.online.food.controller;

import com.online.food.modal.City;
import com.online.food.services.CityService;
import com.online.food.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logger= LoggerFactory.getLogger(AdminController.class);
    
    @Autowired
    private CityService cityService;

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

    @GetMapping("manage-city/{page}")
    public String manageCityPage(@PathVariable("page") int page, Model model){

        try{
            Pageable pageable = PageRequest.of(page, 5);
            Page<City> cities = this.cityService.findAllCityByPagination(pageable);

            model.addAttribute("cities", cities.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", cities.getTotalPages());
            model.addAttribute("title", "Manage-Category");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "admin/manage-city";
    }

    @PostMapping("/add-cityData")
    @ResponseBody
    public String addCityData(@RequestBody City city){
        try{
            City byCityName = this.cityService.findByCityName(city.getCityName());
            if(byCityName == null){
                this.cityService.save(city);
            }
            else {
                this.logger.info("already present this city");
                return "error";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "success";

    }


    @PostMapping("/update-cityData")
    @ResponseBody
    public String updateCityData(@RequestBody City city){
        System.out.println(city.getCityId());
        try {

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }



}
