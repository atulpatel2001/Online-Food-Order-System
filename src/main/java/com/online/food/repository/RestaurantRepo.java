package com.online.food.repository;

import com.online.food.modal.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
}
