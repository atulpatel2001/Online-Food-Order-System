package com.online.food.repository;

import com.online.food.modal.Area;
import com.online.food.modal.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {

    @Query("select s from SubCategory s where s.subCategoryName =:subCategoryName")
    public SubCategory findBySubCategoryName(@Param("subCategoryName") String subCategoryName);

    @Query("select s from SubCategory s where s.category.categoryId =:categoryId")
    public List<SubCategory> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT s from SubCategory s where s.subCategoryName LIKE %:query% OR s.category.categoryName LIKE %:query%")
    List<SubCategory> searchSubCategory(@Param("query") String query);
}
