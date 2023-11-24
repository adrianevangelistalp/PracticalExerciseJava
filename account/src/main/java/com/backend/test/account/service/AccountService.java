package com.backend.test.account.service;

import com.backend.test.account.dto.request.AccountMovementRequestDto;
import com.backend.test.account.dto.request.AccountRequestDto;
import com.backend.test.account.dto.request.AccountUpdateRequestDto;
import com.backend.test.account.dto.response.AccountMovementResponseDto;
import com.backend.test.account.dto.response.AccountResponseDto;

import java.util.List;

public interface AccountService {

    public List<AccountResponseDto> findAll();

    public AccountResponseDto findById(Long id);

    public AccountResponseDto save(AccountRequestDto account);

    public AccountResponseDto update(Long id, AccountUpdateRequestDto account);

    public void delete(Long id);

    public AccountMovementResponseDto registerMovement(Long id, AccountMovementRequestDto accountMovementRequestDto);
}
