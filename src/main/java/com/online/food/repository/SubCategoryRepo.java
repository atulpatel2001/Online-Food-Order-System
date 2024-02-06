package com.online.food.repository;

import com.online.food.modal.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {

    @Query("select s from SubCategory s where s.subCategoryName =:subCategoryName")
    public SubCategory findBySubCategoryName(@Param("subCategoryName") String subCategoryName);
}
