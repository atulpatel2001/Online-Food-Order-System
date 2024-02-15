package com.online.food.services.imple;


import com.online.food.modal.Cart;
import com.online.food.modal.Customer;
import com.online.food.modal.Product;
import com.online.food.repository.CartRepository;
import com.online.food.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImple implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart add(Cart cart) {
        return this.cartRepository.save(cart);
    }

    @Override
    public Cart get(Long id) {
        return this.cartRepository.findById(id).get();
    }

    @Override
    public List<Cart> findAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public void delete(Cart cart) {
              this.cartRepository.delete(cart);
    }

    @Override
    public int countCart(Customer customer) {
        return this.cartRepository.countCart(customer);
    }

    @Override
    public Cart findCartForProductAndCustomer(Customer customer, Product product) {
        return this.cartRepository.findCartForProductAndCustomer(customer,product);
    }
    @Override
    public List<Cart> findCartForCustomer(Customer customer) {
        return this.cartRepository.findCartForCustomer(customer);
    }
}
