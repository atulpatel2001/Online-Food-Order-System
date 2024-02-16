package com.online.food.services.imple;


import com.online.food.modal.Transaction;
import com.online.food.repository.TransactionRepository;
import com.online.food.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImple implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public Transaction add(Transaction transaction) {

        return this.transactionRepository.save(transaction);
    }

    @Override
    public Transaction get(Long id) {
        return this.transactionRepository.findById(id).get();
    }

    @Override
    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }

    @Override
    public void delete(Transaction transaction) {
               this.transactionRepository.delete(transaction);
    }

    @Override
    public Transaction getByOrderId(String orderId) {
        return this.transactionRepository.findByOrderId(orderId);
    }

    @Override
    public Page<Transaction> findAllPagination(Pageable pageable) {
        return this.transactionRepository.findAll(pageable);
    }

    @Override
    public List<Transaction> searchTransaction(String query) {
        return this.transactionRepository.searchTransaction(query);
    }
}
