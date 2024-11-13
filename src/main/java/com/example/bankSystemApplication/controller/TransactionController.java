package com.example.bankSystemApplication.controller;

import com.example.bankSystemApplication.entity.Transaction;
import com.example.bankSystemApplication.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }


    @GetMapping("/range")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(
            @RequestParam Long accountId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Transaction> transactions = transactionService.findTransactionsByDateRange(accountId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/top-spending")
    public ResponseEntity<String> getTopSpendingPlaces() {
        String spendingByPlace = transactionService.getTopSpendingPlace();
        return ResponseEntity.ok(spendingByPlace);
    }

    @GetMapping("/top-spending-date")
    public ResponseEntity<String> getTopSpendingDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        {

            String topSpendingDate = transactionService.getTopSpendingDate(startDate, endDate);
            return ResponseEntity.ok(topSpendingDate);
        }
    }
}
