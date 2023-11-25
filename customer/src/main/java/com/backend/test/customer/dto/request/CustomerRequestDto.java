package com.backend.test.customer.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
