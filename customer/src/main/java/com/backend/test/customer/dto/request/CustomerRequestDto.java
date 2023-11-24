package com.backend.test.customer.dto.request;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private Long personId;
    private String name;
    private String genre;  //TODO enum
    private Integer age;
    private String address;
    private String phone;
    private String password;
    private String state;

}
