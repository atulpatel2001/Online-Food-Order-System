package com.online.food.services.imple;

import com.online.food.modal.Restaurant;
import com.online.food.repository.RestaurantRepo;
import com.online.food.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RestaurantServiceImple implements RestaurantService {
    @Autowired
    private RestaurantRepo restaurantRepo;
    @Override
    public Restaurant save(Restaurant restaurant) {
        return this.restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        return this.restaurantRepo.findById(restaurantId).get();
    }

    @Override
    public List<Restaurant> findAll() {
        return this.restaurantRepo.findAll();
    }

    @Override
    public void delete(Restaurant restaurant) {
        this.restaurantRepo.delete(restaurant);
    }
}
