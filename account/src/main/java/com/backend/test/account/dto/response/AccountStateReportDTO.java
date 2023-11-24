package com.backend.test.account.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data
public class AccountStateReportDTO {
    private Long id;
    private Long customerId;
    private String number;
    private String type;
    private Double balance;
    private String state;

    private List<MovementResponseDto> transactions;
}
