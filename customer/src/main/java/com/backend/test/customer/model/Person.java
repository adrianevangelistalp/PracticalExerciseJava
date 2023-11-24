package com.backend.test.customer.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@MappedSuperclass
@Table(name = "person")
public class Person {

    private Long personId;
    private String name;
    private String genre;  //TODO enum
    private Integer age;
    private String address;
    private String phone;

}