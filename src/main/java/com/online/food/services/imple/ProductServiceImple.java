package com.online.food.services.imple;

import com.online.food.modal.Product;
import com.online.food.repository.ProductRepo;
import com.online.food.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImple implements ProductService {
    @Autowired
    private ProductRepo  productRepo;
    @Override
    public Product save(Product product) {
        return this.productRepo.save(product);
    }

    @Override
    public Product findById(Long productId) {
        return this.productRepo.findById(productId).get();
    }

    @Override
    public List<Product> findAll() {
        return this,productRepo.findAll();
    }

    @Override
    public void delete(Product product) {
        this.productRepo.delete(product);
    }
}