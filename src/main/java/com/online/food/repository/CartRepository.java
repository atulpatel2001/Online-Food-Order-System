package com.online.food.repository;
import com.online.food.modal.Customer;
import com.online.food.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.online.food.modal.Cart;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("SELECT COUNT(c) FROM Cart as c WHERE c.customer =:customer")
    public int countCart(@Param("customer") Customer customer);

    @Query("SELECT c FROM Cart c WHERE c.customer = :customer AND c.product = :product")
    public Cart findCartForProductAndCustomer(@Param("customer") Customer customer, @Param("product") Product product);


    @Query("SELECT c FROM Cart as c WHERE c.customer =:customer")
    public List<Cart> findCartForCustomer(@Param("customer")Customer customer);
}
