package com.backend.test.account.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @Column(nullable = false, unique=true)
    private String number;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    private String state;

    //@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    //private List<Movement> movements;  //todo: confirmar que tengo que sacar cuando termine todo
}