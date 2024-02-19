package com.online.food.repository;

import com.online.food.modal.Staff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaffRepo extends JpaRepository<Staff,Long> {

    @Query("select s from Staff s where s.restaurant.restaurantId =:restaurantId")
    Page<Staff> findByRestaurantId(@Param("restaurantId") Long restaurantId, Pageable pageable);
    @Query("select s from Staff s where s.restaurant.restaurantId =:restaurantId")
    List<Staff> findByRestaurantIdNotPagination(@Param("restaurantId") Long restaurantId);
}
