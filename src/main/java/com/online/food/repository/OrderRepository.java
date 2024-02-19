package com.online.food.repository;


import com.online.food.modal.Customer;
import com.online.food.modal.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String>{

        //find all order for customer dashboard side
        @Query("select o from Order as o where o.customer =:customer")
        public List<Order> findAllOrderParticularCustomer(@Param("customer") Customer customer);

        //find all order particular Restaurant
        @Query("SELECT o FROM Order o WHERE o.product.restaurant.restaurantId =:restaurantId")
        public Page<Order> findAllOrderParticularResturant(@Param("restaurantId")Long restaurantId, Pageable pageable);


        @Query("SELECT o FROM Order o WHERE o.product.restaurant.restaurantId =:restaurantId AND o.orderStatus = 'PENDING'")
        public List<Order> findPendingOrderParticularResturant(@Param("restaurantId")Long restaurantId);


        @Query("SELECT o FROM Order o WHERE o.product.restaurant.restaurantId =:restaurantId AND o.orderStatus = 'DELIVERED'")
        public Page<Order> findDeliveredOrderParticularResturant(@Param("restaurantId")Long restaurantId, Pageable pageable);

        //count pending order for restaurant
        @Query("SELECT COUNT(o) FROM Order o WHERE o.product.restaurant.restaurantId =:restaurantId AND o.orderStatus = 'PENDING'")
        public int countPendingOrderParticularRestaurant(@Param("restaurantId") Long restaurantId);

        //count Delivered order For particular restaurant
        @Query("SELECT COUNT(o) FROM Order o WHERE o.product.restaurant.restaurantId =:restaurantId AND o.orderStatus = 'DELIVERED'")
        public int countDeliveredOrderParticularRestaurant(@Param("restaurantId") Long restaurantId);

        }
