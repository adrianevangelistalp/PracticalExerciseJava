package com.backend.test.account.service;


import com.backend.test.account.dto.request.AccountMovementRequestDto;
import com.backend.test.account.dto.request.AccountRequestDto;
import com.backend.test.account.dto.request.AccountUpdateRequestDto;
import com.backend.test.account.dto.request.MovementRequestDto;
import com.backend.test.account.dto.response.AccountMovementResponseDto;
import com.backend.test.account.dto.response.AccountResponseDto;
import com.backend.test.account.exception.AccountNotFoundException;
import com.backend.test.account.exception.InsufficientFundsException;
import com.backend.test.account.model.Account;
import com.backend.test.account.repository.AccountRepository;
import com.backend.test.account.service.mapping.AccountMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final MovementService movementService;

    @Override
    public List<AccountResponseDto> findAll() {
        return accountMapper.toResponseDto(accountRepository.findAll());
    }

    @Override
    public AccountResponseDto findById(Long id) {
        return accountMapper.toResponseDto(getAccountById(id));
    }

    @Override
    @Transactional
    public AccountResponseDto save(AccountRequestDto account) {
        Account newAccount = accountMapper.toEntity(account);
        newAccount.setBalance(account.getInitialBalance());

        return accountMapper.toResponseDto(accountRepository.save(newAccount));
    }

    @Override
    @Transactional
    public AccountResponseDto update(Long id, AccountUpdateRequestDto account) {
        Account accountToUpdate = getAccountById(id);
        accountToUpdate.setType(account.getType());
        accountToUpdate.setCustomerId(account.getCustomerId());
        accountToUpdate.setState(account.getState());

        return accountMapper.toResponseDto(accountRepository.save(accountToUpdate));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.findById(id);
        accountRepository.deleteById(id);
    }

    @Transactional
    public AccountMovementResponseDto registerMovement(Long id, AccountMovementRequestDto accountMovementRequestDto) {
        Account account = getAccountById(id);
        if (account.getBalance() + accountMovementRequestDto.getAmount() < 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        MovementRequestDto newMovement = new MovementRequestDto();
        newMovement.setAccountId(id);
        newMovement.setAmount(accountMovementRequestDto.getAmount());
        newMovement.setType(accountMovementRequestDto.getType());
        newMovement.setBalance(account.getBalance());
        newMovement.setDate(new Date());
        movementService.save(newMovement);

        account.setBalance(account.getBalance() + accountMovementRequestDto.getAmount());
        accountRepository.save(account);

        return accountMapper.toMovementResponseDto(account);
    }

    protected Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    @Override
    public List<AccountResponseDto> findByCustomerId(Long id) {
        return accountMapper.toResponseDto(accountRepository.findByCustomerId(id));
    }

}
