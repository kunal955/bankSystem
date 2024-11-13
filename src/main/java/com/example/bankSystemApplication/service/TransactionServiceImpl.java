package com.example.bankSystemApplication.service;

import com.example.bankSystemApplication.entity.Account;
import com.example.bankSystemApplication.entity.Transaction;
import com.example.bankSystemApplication.enums.TransactionTypes;
import com.example.bankSystemApplication.exception.BankSystemApplicationException;
import com.example.bankSystemApplication.repositories.AccountRepository;
import com.example.bankSystemApplication.repositories.TransactionRepository;
import com.example.bankSystemApplication.utility.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (transaction.getType() == TransactionTypes.DEBIT) {
            if (account.getBalance() < transaction.getAmount()) {
                throw new BankSystemApplicationException(ValidationMessages.INSUFFICIENT_BALANCE);
            } else {
                account.setBalance(account.getBalance() - transaction.getAmount());
            }
        } else if (transaction.getType() == TransactionTypes.CREDIT) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        account = accountRepository.save(account);

        transaction.setAccount(account);
        transaction.setDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findTransactionsByDateRange(Long accountId, LocalDate startDate, LocalDate endDate) {
        List<Transaction> dbTransactions = transactionRepository.findByAccountIdAndDateBetween(accountId, startDate,endDate);
        return dbTransactions;
    }

    @Override
    public String getTopSpendingPlace() {
        Object[] result = transactionRepository.findTopSpendingPlace();
        Object[] object = (Object[]) result[0];
        String place = object[0].toString();
            Double totalSpent = (Double) object[1];
            return "place = " + place + " total amount spend = " + totalSpent;
    }

    @Override
    public String getTopSpendingDate(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = transactionRepository.findTopSpendingDateBetween(startDate, endDate);

        if (!results.isEmpty()) {
            Object[] topResult = results.get(0);
            LocalDate topDate = (LocalDate) topResult[0];
            Double totalSpent = (Double) topResult[1];
            return "Date = " + topDate + " and = " + totalSpent;
        }
        return "";
    }
}
