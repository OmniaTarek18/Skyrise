package com.example.backend.Services;

import com.example.backend.Entities.Payment;
import com.example.backend.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    private final PaymentRepository paymentRepository ;


    @Autowired
    public PaymentServices(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    public boolean pay(Payment payment) throws Exception {
        try {
            this.paymentRepository.save(payment);
            return true;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
