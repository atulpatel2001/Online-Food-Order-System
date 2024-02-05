package com.online.food.services.imple;

import com.online.food.modal.Area;
import com.online.food.repository.AreaRepo;
import com.online.food.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceImple implements AreaService {
    @Autowired
    private AreaRepo areaRepo;

    @Override
    public Area save(Area area) {
        return this.areaRepo.save(area);
    }

    @Override
    public Area findById(Long areaId) {
        return this.areaRepo.findById(areaId).get();
    }

    @Override
    public List<Area> findAll() {
        return this.areaRepo.findAll();
    }

    @Override
    public void delete(Area area) {
        this.areaRepo.delete(area);
    }
}
