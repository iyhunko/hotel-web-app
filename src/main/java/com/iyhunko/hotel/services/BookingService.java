package com.iyhunko.hotel.services;

import com.iyhunko.hotel.models.Booking;
import com.iyhunko.hotel.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository repository;

    public List<Booking> all() {
        return (List<Booking>) repository.findAll();
    }

    public void save(Booking booking) {
        repository.save(booking);
    }

    public Booking find(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
