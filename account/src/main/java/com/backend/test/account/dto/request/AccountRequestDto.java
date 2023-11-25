package com.backend.test.account.dto.request;

import lombok.Data;

@Data
public class AccountRequestDto {
    private Long customerId;
    private String type;
    private Double initialBalance;
    private Boolean state;
}
