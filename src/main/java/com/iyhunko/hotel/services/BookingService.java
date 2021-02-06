package com.iyhunko.hotel.services;

import com.iyhunko.hotel.models.Booking;
import com.iyhunko.hotel.models.Request;
import com.iyhunko.hotel.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Booking> getWithPagination(int pageNo, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return repository.findAll(pageable);
    }
}
