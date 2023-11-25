package com.backend.test.customer.service.mapping;

import com.backend.test.customer.dto.response.ReportResponseDto;
import com.backend.test.customer.model.Report;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportResponseDto toReportResponseDto(Report report);

    List<ReportResponseDto> toReportResponseDto(List<Report> report);
}
