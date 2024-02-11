package com.online.food.configration.security;


import com.online.food.entity.LoggedInCustomer;
import com.online.food.modal.Customer;
import com.online.food.services.CustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private LoggedInCustomer loggedInCustomer;

    @Autowired
    private CustomerService customerService;


    private Logger logger= LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        this.logger.info("LOGIN CUSTOMER:--"+username);
        Customer customer = this.customerService.findByEmailId(username);
        customer.setEnable(true);
        this.customerService.save(customer);
        this.loggedInCustomer.addUser(username);

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (auth.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/admin/index");
                return;
            } else if (auth.getAuthority().equals("ROLE_RESTAURANT")) {
                response.sendRedirect("/restaurant/index");
                return;
            }
        }
        response.sendRedirect("/order-food/index");


    }

}

