package com.backend.test.account.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "report")
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @Column(nullable = false)
    private Long id;
    private Long customerId;
    @Column(name = "from_date")
    private Date from;
    @Column(name = "to_date")
    private Date to;
    @Column(columnDefinition = "text")
    private String detail;
}