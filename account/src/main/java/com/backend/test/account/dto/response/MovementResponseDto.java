package com.backend.test.account.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class MovementResponseDto {
    private AccountResponseDto account;
    private Date date;
    private String type;
    private Double amount;
    private Double balance;
}
