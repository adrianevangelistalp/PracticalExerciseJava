package com.backend.test.account.service;

import com.backend.test.account.dto.request.MovementRequestDto;
import com.backend.test.account.dto.response.MovementResponseDto;
import com.backend.test.account.exception.AccountNotFoundException;
import com.backend.test.account.exception.MovementNotFoundException;
import com.backend.test.account.model.Account;
import com.backend.test.account.model.Movement;
import com.backend.test.account.repository.AccountRepository;
import com.backend.test.account.repository.MovementRepository;
import com.backend.test.account.service.mapping.MovementMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;
    private final AccountRepository accountRepository;

    @Override
    public List<MovementResponseDto> findAll() {
        return movementMapper.toResponseDto(movementRepository.findAll());
    }

    @Override
    public MovementResponseDto findById(Long id) {
        return movementMapper.toResponseDto(getMovementById(id));
    }

    @Override
    @Transactional
    public MovementResponseDto save(MovementRequestDto movement) {
        Account account = getAccount(movement);
        Movement newMovement = movementMapper.toEntity(movement);
        newMovement.setDate(movement.getDate());
        newMovement.setBalance(movement.getBalance());
        newMovement.setAccount(account);
        return movementMapper.toResponseDto(movementRepository.save(newMovement));
    }

    private Account getAccount(MovementRequestDto movement) {
        return accountRepository.findById(movement.getAccountId()).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    @Override
    @Transactional
    public MovementResponseDto update(Long id, MovementRequestDto movement) {
        Movement movementToUpdate = getMovementById(id);
        Account account = getAccount(movement);
        movementToUpdate.setType(movement.getType());
        movementToUpdate.setAmount(movement.getAmount());
        movementToUpdate.setBalance(account.getBalance() + movement.getAmount());
        return movementMapper.toResponseDto(movementRepository.save(movementToUpdate));
    }

    private Movement getMovementById(Long id) {
        return movementRepository.findById(id).orElseThrow(() -> new MovementNotFoundException("Movement not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.findById(id);
        movementRepository.deleteById(id);
    }

    @Override
    public List<MovementResponseDto> findByAccountIdAndDateBetween(Long id, Date dateFrom, Date dateTo) {
        return movementMapper.toResponseDto(movementRepository.findByAccountIdAndDateBetween(id, dateFrom, dateTo));
    }

}
