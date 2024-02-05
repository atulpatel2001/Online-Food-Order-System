package com.online.food.services;

import com.online.food.modal.Customer;

import java.util.List;

public interface CustomerService {

    public Customer save(Customer customer);

    public Customer findById(Long customerId);
    public List<Customer> findAll();

    public void delete(Customer customer);

    public Customer findByEmailId(String emailId);
}
