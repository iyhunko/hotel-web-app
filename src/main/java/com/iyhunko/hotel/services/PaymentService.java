package com.iyhunko.hotel.services;

import com.iyhunko.hotel.models.Payment;
import com.iyhunko.hotel.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public List<Payment> all() {
        return (List<Payment>) repository.findAll();
    }

    public void save(Payment payment) {
        repository.save(payment);
    }

    public Payment find(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
