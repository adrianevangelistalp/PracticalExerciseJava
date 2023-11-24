package com.backend.test.customer.controller;

import com.backend.test.customer.dto.request.CustomerRequestDto;
import com.backend.test.customer.dto.response.CustomerResponseDto;
import com.backend.test.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> findAll() {
        return ResponseEntity.ok().body(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(customerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> save(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok().body(customerService.save(customerRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> update(@PathVariable("id") Long id, @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.ok().body(customerService.update(id, customerRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/accounts-state")
    public ResponseEntity<String> getAccountsState(@PathVariable("id") Long id,
                                                   @RequestParam(value = "from") @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
                                                   @RequestParam(value = "to") @DateTimeFormat(pattern = "yyyy-MM-dd")  Date to) {
        customerService.getAccountsState(id, from, to);
        return ResponseEntity.ok().body("hola");
    }
}