package com.online.food.services.imple;

import com.online.food.modal.Restaurant;
import com.online.food.repository.RestaurantRepo;
import com.online.food.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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

    @Override
    public Page<Restaurant> findByPagination(Pageable pageable) {
        return this.restaurantRepo.findAll(pageable);
    }

    @Override
    public Restaurant findByCustomerId(Long customerId) {
        return this.restaurantRepo.findByCustomerId(customerId);
    }

    @Override
    public List<Restaurant> searchRestaurant(String query) {
        return this.restaurantRepo.searchRestaurant(query);
    }

    @Override
    public List<Restaurant> findByareaId(Long areaId) {
        return this.restaurantRepo.findByareaId(areaId);
    }
}
