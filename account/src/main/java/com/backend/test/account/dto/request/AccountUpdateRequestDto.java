package com.backend.test.account.dto.request;

import lombok.Data;

@Data
public class AccountUpdateRequestDto {
    private Long customerId;
    private String type;
    private Boolean state;
}
