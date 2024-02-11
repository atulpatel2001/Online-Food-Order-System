package com.online.food.controller;

import com.online.food.helper.FileUploadHelper;
import com.online.food.modal.*;
import com.online.food.services.*;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/order-food")
public class CustomerController {


    @Autowired
    private AreaService areaService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityService cityService;
    @Autowired
    private ComplainService  complainService;

    private Logger logger= LoggerFactory.getLogger((CustomerController.class));


    @Autowired
    private CustomerService customerService;

    @GetMapping("/index")
    public String indexpage(Model model) {
        model.addAttribute("title", "Order-Food | Order Food Online in India");
        return "customer/index";
    }

    @GetMapping("/complain")

    public String complainPage(Model model) {

        model.addAttribute("title", "Order-Food | Complain");
        List<City> cities = this.cityService.findAll();
        model.addAttribute("cities", cities);
        return "customer/complain";
    }

    @GetMapping("/get-areas")
    @ResponseBody
    public List<Area> getAllArea(@RequestParam Long cityId, Model model) {
        List<Area> areas = null;
        try {
            areas = this.areaService.getAreaByCityId(cityId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areas;
    }


    @GetMapping("/get-restaurant")
    @ResponseBody
    public List<Restaurant> getRestaurant(@RequestParam Long areaId, Model model) {
        List<Restaurant> restaurants = null;
        try {
            restaurants = this.restaurantService.findByareaId(areaId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }


    @PostMapping("/add-complain")
    @ResponseBody
    public String addComplainData(@RequestParam("complainSubject") String complainSubject,
                                  @RequestParam("restaurantId") Long restaurantId
                                    , @RequestParam("attachment")MultipartFile attachment
                                        , @RequestParam("complainDescription")String complainDescription, Principal principal){
        try{
            boolean b = FileUploadHelper.uploadFile(attachment, "static/image/complain-img");
            if(b){
                Restaurant restaurant = this.restaurantService.findById(restaurantId);
                Customer customer = this.customerService.findByEmailId(principal.getName().trim());


                Complain build = Complain.builder()
                        .complainSubject(complainSubject)
                        .complainDescription(complainDescription)
                        .attachment(attachment.getOriginalFilename())
                        .complainDate(LocalDate.now())
                        .complainStatus(String.valueOf(Status.PENDING))
                        .restaurant(restaurant)
                        .customer(customer).build();
                this.complainService.save(build);
            }
            else {
                return "file is not upload";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }


}
