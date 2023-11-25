package com.backend.test.account.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AccountStateReportDTO {
    private Long id;
    private Long customerId;
    private String type;
    private Double balance;
    private Boolean state;

    private List<MovementResponseDto> transactions;
}
