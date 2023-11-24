package com.backend.test.customer.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ReportResponseDto {
    private Long id;
    private Long customerId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date from;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date to;

}
