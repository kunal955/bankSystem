package com.example.bankSystemApplication.service;

import com.example.bankSystemApplication.entity.Account;
import com.example.bankSystemApplication.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

}
