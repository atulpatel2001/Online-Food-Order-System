package com.online.food.services;

import com.online.food.modal.SubCategory;

import java.util.List;

public interface SubCategoryService {

    SubCategory save(SubCategory subCategory);

    SubCategory findById(Long subCategoryId);

    List<SubCategory> findAll();

    void delete(SubCategory  subCategory);
}
