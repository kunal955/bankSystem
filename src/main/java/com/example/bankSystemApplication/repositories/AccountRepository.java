package com.example.bankSystemApplication.repositories;

import com.example.bankSystemApplication.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
