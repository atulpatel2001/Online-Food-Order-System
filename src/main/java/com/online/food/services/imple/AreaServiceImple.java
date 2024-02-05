package com.online.food.services.imple;

import com.online.food.modal.Area;
import com.online.food.repository.AreaRepo;
import com.online.food.services.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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

    @Override
    public List<Area> getAreaByCityId(Long cityId) {
        return this.areaRepo.getAreaByCityId(cityId);
    }
}
