package com.backend.test.customer.dto.response;

import lombok.Data;

@Data
public class CustomerResponseDto {
    private Long id;
    private Long personId;
    private String name;
    private String genre;
    private Integer age;
    private String address;
    private String phone;

    private String password;
    private Boolean state;

}
