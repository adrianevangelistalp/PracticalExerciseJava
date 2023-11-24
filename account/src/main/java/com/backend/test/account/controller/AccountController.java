package com.backend.test.account.controller;

import com.backend.test.account.dto.request.AccountMovementRequestDto;
import com.backend.test.account.dto.request.AccountRequestDto;
import com.backend.test.account.dto.request.AccountUpdateRequestDto;
import com.backend.test.account.dto.response.AccountMovementResponseDto;
import com.backend.test.account.dto.response.AccountResponseDto;
import com.backend.test.account.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> findAll() {
        return ResponseEntity.ok().body(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(accountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> save(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.ok().body(accountService.save(accountRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDto> update(@PathVariable("id") Long id, @RequestBody AccountUpdateRequestDto accountRequestDto) {
        return ResponseEntity.ok().body(accountService.update(id, accountRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        accountService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/movement")
    public ResponseEntity<AccountMovementResponseDto> registerMovement(@PathVariable("id") Long id, @RequestBody AccountMovementRequestDto accountMovementRequestDto) {
        return ResponseEntity.ok().body(accountService.registerMovement(id, accountMovementRequestDto));
    }
}
