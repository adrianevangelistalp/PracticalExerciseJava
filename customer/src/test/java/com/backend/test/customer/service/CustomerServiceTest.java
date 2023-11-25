package com.backend.test.customer.service;

import com.backend.test.customer.dto.request.CustomerRequestDto;
import com.backend.test.customer.dto.response.CustomerResponseDto;
import com.backend.test.customer.exception.CustomerNotFoundException;
import com.backend.test.customer.model.Customer;
import com.backend.test.customer.repository.CustomerRepository;
import com.backend.test.customer.service.mapping.CustomerMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    public static final long CUSTOMER_ID = 1L;
    @Mock
    CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    public void before() {
        customerService = new CustomerServiceImpl(customerRepository, new CustomerMapperImpl());
    }

    @Test
    void givenCustomerId_whenFindById_thenReturnOK() {

        CustomerRequestDto customerRequestDto = new CustomerRequestDto(1L,"Juan Osorio","M", 25, "Calle 123", "123456789", "123456", "A");
        Customer customer = getCustomer();

        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(java.util.Optional.of(customer));

        CustomerResponseDto customerResponse = customerService.findById(CUSTOMER_ID);

        verify(customerRepository,times(1)).findById(any());
        assertThat(customerResponse).isNotNull();
        assertThat(customerResponse.getId()).isEqualTo(customer.getId());
        assertThat(customerResponse.getName()).isEqualTo(customer.getName());
        assertThat(customerResponse.getGenre()).isEqualTo(customer.getGenre());
        assertThat(customerResponse.getAge()).isEqualTo(customer.getAge());
        assertThat(customerResponse.getAddress()).isEqualTo(customer.getAddress());
        assertThat(customerResponse.getPhone()).isEqualTo(customer.getPhone());
        assertThat(customerResponse.getPassword()).isEqualTo(customer.getPassword());
        assertThat(customerResponse.getState()).isEqualTo(customer.getState());
    }

    @Test
    void givenNonexistentCustomerId_whenFindById_thenThrowException() {

        CustomerRequestDto customerRequestDto = new CustomerRequestDto(1L,"Juan Osorio","M", 25, "Calle 123", "123456789", "123456", "A");
        Customer customer = getCustomer();

        when(customerRepository.findById(CUSTOMER_ID)).thenThrow(new CustomerNotFoundException("Customer not found"));

        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(CUSTOMER_ID));
        verify(customerRepository,times(1)).findById(any());
    }

    @Test
    void givenCustomer_whenSave_thenReturnOK() {

        CustomerRequestDto customerRequestDto = new CustomerRequestDto(1L,"Juan Osorio","M", 25, "Calle 123", "123456789", "123456", "A");
        Customer customer = getCustomer();

        when(customerRepository.save(any())).thenReturn(customer);

        CustomerResponseDto newCustomer = customerService.save(customerRequestDto);

        verify(customerRepository,times(1)).save(any());
        assertThat(newCustomer).isNotNull();
        assertThat(newCustomer.getId()).isEqualTo(customer.getId());
        assertThat(newCustomer.getName()).isEqualTo(customer.getName());
        assertThat(newCustomer.getGenre()).isEqualTo(customer.getGenre());
        assertThat(newCustomer.getAge()).isEqualTo(customer.getAge());
        assertThat(newCustomer.getAddress()).isEqualTo(customer.getAddress());
        assertThat(newCustomer.getPhone()).isEqualTo(customer.getPhone());
        assertThat(newCustomer.getPassword()).isEqualTo(customer.getPassword());
        assertThat(newCustomer.getState()).isEqualTo(customer.getState());

    }

    @Test
    void givenCustomer_whenUpdate_thenReturnOK() {

        CustomerRequestDto customerRequestDto = new CustomerRequestDto(1L,"Juan Osorio Changed","S", 35, "Calle 123 Changed", "1234567893", "1234563", "B");
        Customer updatedCustomer = getUpdatedCustomer();

        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(java.util.Optional.of(getCustomer()));
        when(customerRepository.save(any())).thenReturn(getUpdatedCustomer());

        CustomerResponseDto updatedCustomerResponse = customerService.update(CUSTOMER_ID, customerRequestDto);

        verify(customerRepository,times(1)).save(any());
        verify(customerRepository,times(1)).findById(any());
        
        assertThat(updatedCustomerResponse).isNotNull();
        assertThat(updatedCustomerResponse.getId()).isEqualTo(updatedCustomer.getId());
        assertThat(updatedCustomerResponse.getName()).isEqualTo(updatedCustomer.getName());
        assertThat(updatedCustomerResponse.getGenre()).isEqualTo(updatedCustomer.getGenre());
        assertThat(updatedCustomerResponse.getAge()).isEqualTo(updatedCustomer.getAge());
        assertThat(updatedCustomerResponse.getAddress()).isEqualTo(updatedCustomer.getAddress());
        assertThat(updatedCustomerResponse.getPhone()).isEqualTo(updatedCustomer.getPhone());
        assertThat(updatedCustomerResponse.getPassword()).isEqualTo(updatedCustomer.getPassword());
        assertThat(updatedCustomerResponse.getState()).isEqualTo(updatedCustomer.getState());

    }

    @Test
    void givenNonexistentCustomer_whenUpdate_thenThrowException() {

        CustomerRequestDto customerRequestDto = new CustomerRequestDto(1L,"Juan Osorio Changed","S", 35, "Calle 123 Changed", "1234567893", "1234563", "B");

        when(customerRepository.findById(CUSTOMER_ID)).thenThrow(new CustomerNotFoundException("Customer not found"));

        assertThrows(CustomerNotFoundException.class, () -> customerService.update(CUSTOMER_ID, customerRequestDto));
        verify(customerRepository,times(1)).findById(any());

    }

    @Test
    void givenCustomer_whenDelete_thenReturnOK() {

        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(java.util.Optional.of(getCustomer()));
        lenient().doNothing().when(customerRepository).delete(any());

        assertDoesNotThrow(() -> customerService.delete(CUSTOMER_ID));
        verify(customerRepository,times(1)).findById(any());
    }

    @Test
    void givenNonexistentCustomer_whenDelete_thenThrowException() {

        when(customerRepository.findById(CUSTOMER_ID)).thenThrow(new CustomerNotFoundException("Customer not found"));

        assertThrows(CustomerNotFoundException.class, () -> customerService.delete(CUSTOMER_ID));
        verify(customerRepository,times(1)).findById(any());

    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setPersonId(11L);
        customer.setName("Juan Osorio");
        customer.setGenre("M");
        customer.setAge(25);
        customer.setAddress("Calle 123");
        customer.setPhone("123456789");
        customer.setPassword("123456");
        customer.setState("A");
        return customer;
    }

    private Customer getUpdatedCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setPersonId(11L);
        customer.setName("Juan Osorio Changed");
        customer.setGenre("S");
        customer.setAge(35);
        customer.setAddress("Calle 123 Changed");
        customer.setPhone("1234567893");
        customer.setPassword("1234563");
        customer.setState("B");
        return customer;
    }
}