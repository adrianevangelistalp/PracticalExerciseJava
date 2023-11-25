package com.backend.test.account.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "movement")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false, length = 20)
    private String type;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private Double balance;
}