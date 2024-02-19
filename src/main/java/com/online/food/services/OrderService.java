package com.online.food.services;

import com.online.food.modal.Customer;
import com.online.food.modal.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {


    public Order add(Order order);

    public Order get(String id);


    public List<Order>  findAll();

    public void delete(Order order);

    public List<Order>  findAllCustomerOrder(Customer customer);



    public Page<Order> findAllOrderParticularRestaurant(Long restaurnatId, Pageable pageable);


    public int countPendingOrderParticularRestaurant(Long restaurantId);

    public int countDeliveredOrderParticularRestaurant(Long restaurantId);

    List<Order> findPendingOrderParticularResturant(Long restaurantId);
    Page<Order> findDeliveredOrderParticularResturant(Long restaurantId,Pageable pageable);


}

