package com.backend.test.customer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStateReportDTO {
    private Long id;
    private Long customerId;
    private String type;
    private Double balance;
    private Boolean state;

    private List<MovementResponseDto> transactions;
}

