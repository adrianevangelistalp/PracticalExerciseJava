package com.backend.test.account.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MovementCompleteResponseDto {
    private Long id;
    private Long accountId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String type;
    private Double balance;
    private Double amount;

}
