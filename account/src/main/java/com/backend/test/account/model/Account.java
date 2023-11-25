package com.backend.test.account.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private Long customerId;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private Boolean state;

}