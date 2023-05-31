package com.banking.bankapi.repositories;

import com.banking.bankapi.dto.TransactionDto;
import com.banking.bankapi.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByUserId(Integer userId);
}
