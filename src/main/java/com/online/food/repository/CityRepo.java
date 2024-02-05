package com.online.food.repository;

import com.online.food.modal.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepo extends JpaRepository<City,Long> {

    City findByCityName(String cityName);
}
