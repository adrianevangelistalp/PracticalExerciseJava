package com.backend.test.account.repository;

import com.backend.test.account.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    List<Movement> findByAccountIdAndDateBetween(Long id, Date from, Date to);
}