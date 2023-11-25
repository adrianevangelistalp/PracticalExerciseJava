package com.backend.test.customer.service;

import com.backend.test.customer.dto.response.ReportResponseDto;
import com.backend.test.customer.exception.ReportNotFoundException;
import com.backend.test.customer.model.messages.AccountStateRequestMessage;
import com.backend.test.customer.repository.ReportRepository;
import com.backend.test.customer.service.mapping.ReportMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;
    private final MessageQueueService messageQueueService;


    @Override
    public List<ReportResponseDto> findByCustomerId(Long id) {
        return reportMapper.toReportResponseDto(reportRepository.findAllByCustomerId(id));
    }

    @Override
    public String findById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new ReportNotFoundException("Report not found")).getDetail();
    }

    @Override
    public void getAccountsState(Long id, Date from, Date to) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        AccountStateRequestMessage accountStateRequestMessage = new AccountStateRequestMessage(id,formatter.format(from),formatter.format(to));
        messageQueueService.sendAccountStateRequest(accountStateRequestMessage);
    }
}
