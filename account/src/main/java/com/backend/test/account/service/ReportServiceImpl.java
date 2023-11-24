package com.backend.test.account.service;

import com.backend.test.account.dto.response.AccountStateReportDTO;
import com.backend.test.account.model.Movement;
import com.backend.test.account.model.Report;
import com.backend.test.account.repository.AccountRepository;
import com.backend.test.account.repository.MovementRepository;
import com.backend.test.account.repository.ReportRepository;
import com.backend.test.account.service.mapping.AccountMapper;
import com.backend.test.account.service.mapping.MovementMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final AccountService accountService;
    private final MovementService movementService;
    private final ReportRepository reportRepository;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public void createAccountsState(Long id, String from, String to) {
        try {
            Date dateFrom = new SimpleDateFormat("yyyy-MM-dd").parse(from);
            Date dateTo = new SimpleDateFormat("yyyy-MM-dd").parse(to);

            List<AccountStateReportDTO> accountsState = accountService.findByCustomerId(id).stream().map(account -> {
                AccountStateReportDTO accountState = accountMapper.toAccountStateReportDTO(account);
                accountState.setTransactions(movementService.findByAccountIdAndDateBetween(account.getId(), dateFrom, dateTo));
                return accountState;
            }).collect(Collectors.toList());
            Report report = new Report();
            report.setCustomerId(id);
            report.setFrom(dateFrom);
            report.setTo(dateTo);
            report.setDetail(accountsState.toString());
            reportRepository.save(report);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
