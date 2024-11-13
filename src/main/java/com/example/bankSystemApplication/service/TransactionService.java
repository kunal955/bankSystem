package com.example.bankSystemApplication.service;

import com.example.bankSystemApplication.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    List<Transaction> findTransactionsByDateRange(Long accountId, LocalDate startDate, LocalDate endDate);

    String getTopSpendingPlace();

    String getTopSpendingDate(LocalDate startDate, LocalDate endDate);
}
