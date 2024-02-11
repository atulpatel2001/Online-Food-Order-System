package com.online.food.repository;

import com.online.food.modal.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepo extends JpaRepository<Complain,Long> {
}
