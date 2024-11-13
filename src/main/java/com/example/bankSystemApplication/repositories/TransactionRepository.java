package com.example.bankSystemApplication.repositories;

import com.example.bankSystemApplication.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountIdAndDateBetween(Long id, LocalDate startDate, LocalDate endDate);

    @Query("SELECT t.name, SUM(t.amount) AS totalSpent " +
            "FROM Transaction t " +
            "WHERE t.deleted = false " +
            "AND t.type = 'DEBIT'" +
            "GROUP BY t.name " +
            "ORDER BY totalSpent DESC")
    Object[] findTopSpendingPlace();

    @Query("SELECT t.date, SUM(t.amount) AS totalSpent " +
            "FROM Transaction t " +
            "WHERE t.date BETWEEN :startDate AND :endDate AND t.type = 'DEBIT' AND t.deleted = false " +
            "GROUP BY t.date " +
            "ORDER BY totalSpent DESC")
    List<Object[]> findTopSpendingDateBetween(LocalDate startDate, LocalDate endDate);

}
