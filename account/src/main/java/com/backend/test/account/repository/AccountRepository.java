package com.backend.test.account.repository;

import com.backend.test.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long id);
}