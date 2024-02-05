package com.online.food.repository;

import com.online.food.modal.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {
}
