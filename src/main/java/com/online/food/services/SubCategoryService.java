package com.online.food.services;

import com.online.food.modal.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubCategoryService {

    SubCategory save(SubCategory subCategory);

    SubCategory findById(Long subCategoryId);

    List<SubCategory> findAll();

    void delete(SubCategory  subCategory);

    Page<SubCategory> findByPagination(Pageable pageable);

    SubCategory findBySubCategoryName(String subCategoryName);

    List<SubCategory> findByCategoryId(Long categoryId);

    List<SubCategory> searchSubCategory(String query);
}
