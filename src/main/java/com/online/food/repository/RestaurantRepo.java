package com.online.food.repository;

import com.online.food.modal.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
    @Query("select r from Restaurant r where r.customer.customerId =:customerId")
    Restaurant findByCustomerId(@Param("customerId") Long customerId);
}
