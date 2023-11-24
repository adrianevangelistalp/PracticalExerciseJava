package com.backend.test.account.dto.request;

import lombok.Data;

@Data
public class MovementRequestDto {
    private Long accountId;
    private String type;
    private Double amount;
}
