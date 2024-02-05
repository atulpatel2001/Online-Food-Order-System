package com.online.food.services.imple;

import com.online.food.modal.City;
import com.online.food.repository.CityRepo;
import com.online.food.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CityServiceImple implements CityService {
    @Autowired
    private CityRepo cityRepo;

    @Override
    public City save(City city) {
        return this.cityRepo.save(city);
    }

    @Override
    public City findById(Long cityId) {
        return this.cityRepo.findById(cityId).get();
    }

    @Override
    public List<City> findAll() {
        return this.cityRepo.findAll();
    }

    @Override
    public void delete(City city) {
        this.cityRepo.delete(city);
    }
}
