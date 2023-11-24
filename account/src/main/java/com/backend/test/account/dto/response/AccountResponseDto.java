package com.backend.test.account.dto.response;

import lombok.Data;

@Data
public class AccountResponseDto {
    private Long id;
    private Long customerId;
    private String number;
    private String type;
    private Double balance;
    private String state;
}
