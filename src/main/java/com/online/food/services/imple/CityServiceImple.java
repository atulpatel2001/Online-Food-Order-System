package com.online.food.services.imple;

import com.online.food.modal.City;
import com.online.food.repository.CityRepo;
import com.online.food.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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

    @Override
    public Page<City> findAllCityByPagination(Pageable pageable) {
        return this.cityRepo.findAll(pageable);
    }

    @Override
    public City findByCityName(String cityName) {
        return this.cityRepo.findByCityName(cityName);
    }

    @Override
    public List<City> searchByTitle(String cityName) {
        return this.cityRepo.searchByTitle(cityName);
    }
}
