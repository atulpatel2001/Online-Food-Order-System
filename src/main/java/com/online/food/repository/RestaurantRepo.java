package com.online.food.repository;

import com.online.food.modal.Area;
import com.online.food.modal.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
    @Query("select r from Restaurant r where r.customer.customerId =:customerId")
    Restaurant findByCustomerId(@Param("customerId") Long customerId);

    @Query("select r from Restaurant r where r.restaurantName LIKE %:query% OR r.city.cityName LIKE %:query%")
    List<Restaurant> searchRestaurant(@Param("query") String query);

    @Query("select r from Restaurant r where r.area.areaId =:areaId")
    List<Restaurant> findByareaId(@Param("areaId") Long areaId);

    @Query("select r from Restaurant r where r.city.cityName =:cityName")
    List<Restaurant> findByCityName(@Param("cityName") String cityName);


}
