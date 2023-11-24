package com.backend.test.customer.service;

import com.backend.test.customer.dto.request.CustomerRequestDto;
import com.backend.test.customer.dto.response.CustomerResponseDto;
import com.backend.test.customer.exception.CustomerNotFoundException;
import com.backend.test.customer.messaging.publisher.RabbitMQProducer;
import com.backend.test.customer.model.Customer;
import com.backend.test.customer.model.messages.AccountStateRequestMessage;
import com.backend.test.customer.repository.CustomerRepository;
import com.backend.test.customer.service.mapping.CustomerMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RabbitMQProducer rabbitMQProducer;
    @Override
    public List<CustomerResponseDto> findAll() {
        return customerMapper.toResponseDto(customerRepository.findAll());
    }

    @Override
    public CustomerResponseDto findById(Long id) {
        return customerMapper.toResponseDto(getCustomerById(id));
    }

    @Override
    @Transactional
    public CustomerResponseDto save(CustomerRequestDto customer) {
        return customerMapper.toResponseDto(customerRepository.save(customerMapper.toEntity(customer)));
    }

    @Override
    @Transactional
    public CustomerResponseDto update(Long id, CustomerRequestDto customer) {
        Customer customerToUpdate = getCustomerById(id);
        customerToUpdate.setPersonId(customer.getPersonId());
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setGenre(customer.getGenre());
        customerToUpdate.setAge(customer.getAge());
        customerToUpdate.setAddress(customer.getAddress());
        customerToUpdate.setPhone(customer.getPhone());
        customerToUpdate.setPassword(customer.getPassword());
        customerToUpdate.setState(customer.getState());
        return customerMapper.toResponseDto(customerRepository.save(customerToUpdate));
    }

    private Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        getCustomerById(id);
        customerRepository.deleteById(id);
    }

    @Override
    public void getAccountsState(Long id, Date from, Date to) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        AccountStateRequestMessage accountStateRequestMessage = new AccountStateRequestMessage(id,formatter.format(from),formatter.format(to));
        rabbitMQProducer.send(accountStateRequestMessage);
    }
}
