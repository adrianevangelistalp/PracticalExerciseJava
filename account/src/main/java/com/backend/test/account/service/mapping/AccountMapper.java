package com.backend.test.account.service.mapping;

import com.backend.test.account.dto.request.AccountRequestDto;
import com.backend.test.account.dto.response.AccountMovementResponseDto;
import com.backend.test.account.dto.response.AccountResponseDto;
import com.backend.test.account.dto.response.AccountStateReportDTO;
import com.backend.test.account.model.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    List<AccountResponseDto> toResponseDto(List<Account> accounts);

    AccountResponseDto toResponseDto(Account account);

    Account toEntity(AccountRequestDto account);

    AccountMovementResponseDto toMovementResponseDto(Account account);

    AccountStateReportDTO toAccountStateReportDTO(AccountResponseDto account);
}
