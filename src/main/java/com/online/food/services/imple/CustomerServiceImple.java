package com.online.food.services.imple;

import com.online.food.modal.Customer;
import com.online.food.repository.CustomerRepo;
import com.online.food.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImple implements CustomerService {


    private CustomerRepo customerRepo;
    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer findById(Long customerId) {
        return this.customerRepo.findById(customerId).get();
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepo.findAll();
    }

    @Override
    public void delete(Customer customer) {
        this.customerRepo.delete(customer);
    }

    @Override
    public Customer findByEmailId(String emailId) {
        return this.customerRepo.findByCustomerEmailId(emailId);
    }
}
