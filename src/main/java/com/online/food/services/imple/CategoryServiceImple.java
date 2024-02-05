package com.online.food.services.imple;

import com.online.food.modal.Category;
import com.online.food.repository.CategoryRepo;
import com.online.food.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImple implements CategoryService {
    @Autowired
    private CategoryRepo  categoryRepo;
    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category findById(Long categoryId) {
        return this.categoryRepo.findById(categoryId).get();
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepo.findAll();
    }

    @Override
    public void delete(Category category) {
        this.categoryRepo.delete(category);
    }
}
