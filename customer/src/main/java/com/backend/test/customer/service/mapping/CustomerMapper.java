package com.backend.test.customer.service.mapping;

import com.backend.test.customer.dto.request.CustomerRequestDto;
import com.backend.test.customer.dto.response.CustomerResponseDto;
import com.backend.test.customer.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    List<CustomerResponseDto> toResponseDto(List<Customer> customers);

    CustomerResponseDto toResponseDto(Customer customer);

    Customer toEntity(CustomerRequestDto customerRequestDTO);
}
