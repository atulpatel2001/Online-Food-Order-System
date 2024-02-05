package com.online.food.repository;

import com.online.food.modal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {

    @Query("select c from Customer c where c.customerEmail =:customerEmail")
    Customer findByCustomerEmailId(@Param("customerEmail") String customerEmail);
}
