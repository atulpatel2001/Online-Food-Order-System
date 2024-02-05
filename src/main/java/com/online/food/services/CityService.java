package com.online.food.services;

import com.online.food.modal.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

    City save(City city);

    City findById(Long cityId);

    List<City> findAll();

    void delete(City city);

    Page<City> findAllCityByPagination(Pageable pageable);

    City findByCityName(String cityName);
}
