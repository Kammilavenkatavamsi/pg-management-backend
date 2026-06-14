package com.pg.pgmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private LocalDate paymentDate;
    private String month;
    private String status; // PAID, PENDING

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}