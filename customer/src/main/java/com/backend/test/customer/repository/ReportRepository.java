package com.backend.test.customer.repository;

import com.backend.test.customer.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByCustomerId(Long id);
}