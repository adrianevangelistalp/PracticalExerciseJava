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
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @Column(nullable = false)
    private Long id;
    @Column(length = 20)
    private String password;
    private Boolean state;
}