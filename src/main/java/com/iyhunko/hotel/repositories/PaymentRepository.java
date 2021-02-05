package com.iyhunko.hotel.repositories;

import com.iyhunko.hotel.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
