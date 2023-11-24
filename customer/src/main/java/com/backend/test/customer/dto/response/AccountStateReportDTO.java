package com.backend.test.customer.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStateReportDTO {
    private Long id;
    private Long customerId;
    private String number;
    private String type;
    private Double balance;
    private String state;

    private List<MovementResponseDto> transactions;
}

