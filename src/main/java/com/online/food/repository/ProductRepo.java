package com.online.food.repository;

import com.online.food.modal.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.restaurant.restaurantId =:restaurantId")
    Page<Product> findProductForRestaurant(@Param("restaurantId")Long restaurantId, Pageable pageable);
    @Query("select p from Product as p where p.subCategory.category.categoryName = 'Vegitarian' ORDER BY p.productId DESC LIMIT 8")
    public List<Product> findVegitarianProduct();

    @Query("SELECT p FROM Product p WHERE p.restaurant.restaurantId =:restaurantId")
    List<Product> findByRestaurantId(@Param("restaurantId")Long restaurantId);


}
