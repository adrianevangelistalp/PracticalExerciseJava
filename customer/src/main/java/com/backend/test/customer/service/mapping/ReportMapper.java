package com.backend.test.customer.service.mapping;

import com.backend.test.customer.dto.request.CustomerRequestDto;
import com.backend.test.customer.dto.response.CustomerResponseDto;
import com.backend.test.customer.dto.response.ReportResponseDto;
import com.backend.test.customer.model.Customer;
import com.backend.test.customer.model.Report;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportResponseDto toReportResponseDto(Report report);

    List<ReportResponseDto> toReportResponseDto(List<Report> report);
}
