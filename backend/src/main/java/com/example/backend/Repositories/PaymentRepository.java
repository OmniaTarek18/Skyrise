package com.example.backend.Repositories;

import com.example.backend.Entities.CompositeKeys.PaymentPK;
import com.example.backend.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, PaymentPK> {

}
