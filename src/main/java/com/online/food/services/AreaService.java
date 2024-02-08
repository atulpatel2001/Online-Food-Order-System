package com.online.food.services;

import com.online.food.modal.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaService {

    Area save(Area area);

    Area findById(Long areaId);

    List<Area> findAll();

    void delete(Area area);

    List<Area> getAreaByCityId(Long cityId);

    Page<Area> findAreaByPagination(Pageable pageable);

    Area getAreaByName(String areaName);

    List<Area> searchArea(String query);
}
