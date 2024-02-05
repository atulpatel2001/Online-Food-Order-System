package com.online.food.services;

import com.online.food.modal.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product findById(Long productId);

    List<Product> findAll();


    void delete(Product product);
}
