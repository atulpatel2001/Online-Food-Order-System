package com.online.food.services.imple;

import com.online.food.modal.Complain;
import com.online.food.repository.ComplainRepo;
import com.online.food.services.ComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplainServiceImple implements ComplainService {

    @Autowired
    private ComplainRepo complainRepo;
    @Override
    public Complain save(Complain complain) {
        return this.complainRepo.save(complain);
    }

    @Override
    public Complain findById(Long id) {
        return this.complainRepo.findById(id).get();
    }

    @Override
    public List<Complain> findAll() {
        return this.complainRepo.findAll();
    }

    @Override
    public void delete(Complain complain) {
        this.complainRepo.delete(complain);
    }
}
