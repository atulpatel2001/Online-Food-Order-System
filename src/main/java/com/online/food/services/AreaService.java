package com.online.food.services;

import com.online.food.modal.Area;

import java.util.List;

public interface AreaService {

    Area save(Area area);

    Area findById(Long areaId);

    List<Area> findAll();

    void delete(Area area);

    List<Area> getAreaByCityId(Long cityId);
}
