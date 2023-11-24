package com.backend.test.customer.service;

import com.backend.test.customer.dto.response.ReportResponseDto;

import java.util.Date;
import java.util.List;

public interface ReportService {
    void getAccountsState(Long id, Date from, Date to);

    List<ReportResponseDto> findByCustomerId(Long id);

    String findById(Long id);
}
