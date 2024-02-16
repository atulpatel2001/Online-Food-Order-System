package com.online.food.repository;


import com.online.food.modal.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Long> {

    @Query("select a from Address as a where a.customer.customerId =:customerId")
    public List<Address> findByCustomerId(Long customerId);
}
