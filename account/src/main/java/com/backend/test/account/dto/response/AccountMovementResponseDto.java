package com.backend.test.account.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class AccountMovementResponseDto {
    private Long id;
    private Double balance;
}
