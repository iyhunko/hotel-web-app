package com.iyhunko.hotel.repositories;

import com.iyhunko.hotel.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
