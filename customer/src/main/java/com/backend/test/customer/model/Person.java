package com.backend.test.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@MappedSuperclass
@Table(name = "person")
public class Person {

    private Long personId;
    private String name;
    @Column(length = 20)
    private String genre;
    private Integer age;
    private String address;
    @Column(length = 30)
    private String phone;

}