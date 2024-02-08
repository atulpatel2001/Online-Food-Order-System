package com.online.food.services;

import com.online.food.modal.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    Restaurant findById(Long restaurantId);

    List<Restaurant> findAll();

    void delete(Restaurant  restaurant);


    Page<Restaurant> findByPagination(Pageable pageable);

    Restaurant findByCustomerId(Long customerId);

    List<Restaurant> searchRestaurant(String query);
}
