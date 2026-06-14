package com.pg.pgmanagement.service;

import com.pg.pgmanagement.entity.Payment;
import com.pg.pgmanagement.entity.Tenant;
import com.pg.pgmanagement.repository.PaymentRepository;
import com.pg.pgmanagement.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TenantRepository tenantRepository;

    public Payment addPayment(Long tenantId, Payment payment) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found with id: " + tenantId));

        payment.setTenant(tenant);
        payment.setPaymentDate(LocalDate.now());
        payment.setStatus("PAID");

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByTenant(Long tenantId) {
        return paymentRepository.findByTenantId(tenantId);
    }
}
