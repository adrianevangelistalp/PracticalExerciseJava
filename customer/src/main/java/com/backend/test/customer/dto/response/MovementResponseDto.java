package com.backend.test.customer.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponseDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String type;
    private Double balance;
    private Double amount;

}