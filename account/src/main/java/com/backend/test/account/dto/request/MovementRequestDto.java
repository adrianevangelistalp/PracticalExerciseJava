package com.backend.test.account.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MovementRequestDto {
    private Long accountId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String type;
    private Double amount;
    private Double balance;
}
