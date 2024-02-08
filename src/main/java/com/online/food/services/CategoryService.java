package com.online.food.services;

import com.online.food.modal.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    Category findById(Long categoryId);

    List<Category> findAll();

    void delete(Category category);

    Page<Category> findByPagination(Pageable pageable);

    Category getByCategoryName(String categoryName);

    List<Category> searchByCategoryName(String query);
}
