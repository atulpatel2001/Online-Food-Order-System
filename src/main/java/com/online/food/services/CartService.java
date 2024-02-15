package com.online.food.services;


import com.online.food.modal.Cart;
import com.online.food.modal.Customer;
import com.online.food.modal.Product;

import java.util.List;

public interface CartService {

    public Cart add(Cart cart);

    public Cart get(Long id);

    public List<Cart> findAll();


    public void delete(Cart cart);


    public int countCart(Customer customer);


    public Cart findCartForProductAndCustomer(Customer customer, Product product);


    public List<Cart> findCartForCustomer(Customer customer);
}
