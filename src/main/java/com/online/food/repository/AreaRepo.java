package com.online.food.repository;

import com.online.food.modal.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepo extends JpaRepository<Area,Long> {
}
