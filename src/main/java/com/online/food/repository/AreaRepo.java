package com.online.food.repository;

import com.online.food.modal.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepo extends JpaRepository<Area,Long> {
    @Query("select a from Area a where a.city.cityId =:cityId")
    public List<Area> getAreaByCityId(@Param("cityId") Long cityId);
    @Query("select a from Area a where a.areaName =:areaName")
    public Area getAreaByName(@Param("areaName") String areaName);
}
