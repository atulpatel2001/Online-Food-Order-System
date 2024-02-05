package com.online.food.services;

import com.online.food.modal.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    Category findById(Long categoryId);

    List<Category> findAll();

    void delete(Category category);
}
