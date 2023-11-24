package com.backend.test.account.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class MovementRequestDto {
    private Long accountId;
    private Date date;
    private String type;
    private Double amount;
    private Double balance;
}
