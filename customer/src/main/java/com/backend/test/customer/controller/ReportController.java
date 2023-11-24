package com.backend.test.customer.controller;

import com.backend.test.customer.dto.response.AccountStateReportDTO;
import com.backend.test.customer.dto.response.ReportResponseDto;
import com.backend.test.customer.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{customerId}")
    public ResponseEntity getAccountsState(@PathVariable("customerId") Long id,
                                           @RequestParam(value = "from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                           @RequestParam(value = "to") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date to) {
        reportService.getAccountsState(id, from, to);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReportResponseDto>> findByCustomerId(@PathVariable("customerId") Long id) {
        return ResponseEntity.ok().body(reportService.findByCustomerId(id));
    }

    @GetMapping("/{id}/detail")
    public ResponseEntity<List<AccountStateReportDTO>> findById(@PathVariable("id") Long id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AccountStateReportDTO> report = objectMapper.readValue(reportService.findById(id), new TypeReference<List<AccountStateReportDTO>>() {});

        return ResponseEntity.ok().body(report);
    }


}
