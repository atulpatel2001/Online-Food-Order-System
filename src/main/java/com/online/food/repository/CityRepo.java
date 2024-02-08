package com.online.food.repository;

import com.online.food.modal.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepo extends JpaRepository<City,Long> {

    City findByCityName(String cityName);

    @Query(" select c from City as c where c.cityName LIKE %:name%")
    public List<City> searchByTitle(@Param("name") String name);
}
