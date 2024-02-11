package com.online.food.repository;

import com.online.food.modal.Complain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ComplainRepo extends JpaRepository<Complain,Long> {
    @Query("select c from Complain c where c.restaurant.restaurantId =: restaurantId")
    Page<Complain> findByPainationWithRestaurantId(Pageable pageable, @Param("restaurantId")Long restaurantId);
}
