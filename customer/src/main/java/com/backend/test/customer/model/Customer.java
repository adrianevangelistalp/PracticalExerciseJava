package com.backend.test.customer.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "customer")
@EqualsAndHashCode(callSuper=false)
public class Customer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    private String password;
    private String state;
}