package com.online.food.repository;

import com.online.food.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    @Query("select c from Category c where c.categoryName =:categoryName")
    public Category getByCategoryName(@Param("categoryName") String categoryName);
}
