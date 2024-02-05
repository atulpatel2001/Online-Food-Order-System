package com.online.food.controller;

import com.online.food.modal.Customer;
import com.online.food.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class HomeController {
@Autowired
    private CustomerService customerService;

@Autowired
private BCryptPasswordEncoder passwordEncoder;

private Logger logger= LoggerFactory.getLogger(HomeController.class);
    //sign in page
    @GetMapping("/signin")
    public String signin(Model model){
        model.addAttribute("title","Customer | Login-Page");
        return "signin";
    }

    //signup page
    @GetMapping("/customer-signup")
    public String signup(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("title", "Customer | Register-Page");
        return "signup";
    }

    //register data handle
    @PostMapping("/doSignup")
    @ResponseBody
    public ResponseEntity<?> doSignup(@ModelAttribute("customer") Customer customer) {
        try {

            Customer customer1 = this.customerService.findByEmailId(customer.getCustomerEmail());
            if (customer1 ==  null){
                customer.setEnable(false);
                customer.setCustomerJoinDate(LocalDateTime.now());
                customer.setCustomerRole("ROLE_CUSTOMER");
                customer.setCustomerPassword(this.passwordEncoder.encode(customer.getCustomerPassword()));
                this.customerService.save(customer);
                this.logger.info("SuccessFull Register " + customer.getCustomerEmail());
            }
            else {
                return ResponseEntity.ok("Already Register This Email!!");
            }
        } catch (Exception e) {
            this.logger.info("Something Went Wrong " + customer.getCustomerEmail());
            return ResponseEntity.ok("error");
        }

        return ResponseEntity.ok("success");

    }


}
