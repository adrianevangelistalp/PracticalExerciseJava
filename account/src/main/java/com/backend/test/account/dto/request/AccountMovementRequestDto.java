package com.backend.test.account.dto.request;

import lombok.Data;

@Data
public class AccountMovementRequestDto {
    private String type;
    private Double amount;
}
