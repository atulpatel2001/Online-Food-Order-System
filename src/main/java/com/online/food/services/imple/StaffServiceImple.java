package com.online.food.services.imple;

import com.online.food.modal.Staff;
import com.online.food.repository.StaffRepo;
import com.online.food.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImple implements StaffService {

    @Autowired
    private StaffRepo staffRepo;
    @Override
    public Staff save(Staff staff) {
        return this.staffRepo.save(staff);
    }

    @Override
    public Staff findById(Long id) {
        return this.staffRepo.findById(id).get();
    }

    @Override
    public List<Staff> findAll() {
        return this.staffRepo.findAll();
    }

    @Override
    public void delete(Staff staff) {
        this.staffRepo.delete(staff);
    }

    @Override
    public Page<Staff> findByRestaurantId(Long restaurantId, Pageable pageable) {
        return this.staffRepo.findByRestaurantId(restaurantId,pageable);
    }

    @Override
    public List<Staff> findByRestaurantIdNotPagination(Long restaurantId) {
        return this.staffRepo.findByRestaurantIdNotPagination(restaurantId);
    }
}
