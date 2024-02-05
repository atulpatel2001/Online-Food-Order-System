package com.online.food.services.imple;

import com.online.food.modal.SubCategory;
import com.online.food.repository.SubCategoryRepo;
import com.online.food.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubCategoryServiceImple implements SubCategoryService {
    @Autowired
    private SubCategoryRepo categoryRepo;
    @Override
    public SubCategory save(SubCategory subCategory) {
        return this.categoryRepo.save(subCategory);
    }

    @Override
    public SubCategory findById(Long subCategoryId) {
        return this.categoryRepo.findById(subCategoryId).get();
    }

    @Override
    public List<SubCategory> findAll() {
        return this.categoryRepo.findAll();
    }

    @Override
    public void delete(SubCategory subCategory) {
        this.categoryRepo.delete(subCategory);
    }
}
