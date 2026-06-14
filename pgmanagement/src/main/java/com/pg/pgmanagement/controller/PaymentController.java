package com.pg.pgmanagement.controller;

import com.pg.pgmanagement.entity.Payment;
import com.pg.pgmanagement.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/tenant/{tenantId}")
    public Payment addPayment(@PathVariable Long tenantId, @RequestBody Payment payment) {
        return paymentService.addPayment(tenantId, payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/tenant/{tenantId}")
    public List<Payment> getPaymentsByTenant(@PathVariable Long tenantId) {
        return paymentService.getPaymentsByTenant(tenantId);
    }
}
