package com.online.food.services;

import com.online.food.modal.Complain;

import java.util.List;

public interface ComplainService {


    Complain save(Complain complain);

    Complain findById(Long id);

    List<Complain> findAll();

    void delete(Complain complain);
}
