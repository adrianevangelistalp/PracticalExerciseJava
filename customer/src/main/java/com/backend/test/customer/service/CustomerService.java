package com.backend.test.customer.service;

import com.backend.test.customer.dto.request.CustomerRequestDto;
import com.backend.test.customer.dto.response.CustomerResponseDto;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    public List<CustomerResponseDto> findAll();

    public CustomerResponseDto findById(Long id);

    public CustomerResponseDto save(CustomerRequestDto customer);

    public CustomerResponseDto update(Long id, CustomerRequestDto customer);

    public void delete(Long id);


    void getAccountsState(Long id, Date from, Date to);
}
