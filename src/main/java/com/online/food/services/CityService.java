package com.online.food.services;

import com.online.food.modal.City;

import java.util.List;

public interface CityService {

    City save(City city);

    City findById(Long cityId);

    List<City> findAll();

    void delete(City city);
}
