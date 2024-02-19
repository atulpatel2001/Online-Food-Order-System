package com.online.food.services;

import com.online.food.modal.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface StaffService {

    Staff save(Staff staff);

    Staff findById(Long id);

    List<Staff> findAll();

    void delete(Staff staff);

    Page<Staff> findByRestaurantId(Long restaurantId, Pageable pageable);
    List<Staff> findByRestaurantIdNotPagination(Long restaurantId);
}
