package com.online.food.services;

import com.online.food.modal.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    Restaurant findById(Long restaurantId);

    List<Restaurant> findAll();

    void delete(Restaurant  restaurant);
}
