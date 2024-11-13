package com.example.bankSystemApplication.controller;

import com.example.bankSystemApplication.entity.Account;
import com.example.bankSystemApplication.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping
    public ResponseEntity<Account> saveAccount(@Valid @RequestBody Account account){
        Account createdAccount = accountService.saveAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

}
