package com.backend.test.customer.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    private Customer customer;
    @Column(name = "from_date")
    private Date from;
    @Column(name = "to_date")
    private Date to;
    private String detail;

}