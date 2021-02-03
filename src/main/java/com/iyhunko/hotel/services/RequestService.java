package com.iyhunko.hotel.services;

import com.iyhunko.hotel.models.Request;
import com.iyhunko.hotel.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestRepository repository;

    public List<Request> all() {
        return (List<Request>) repository.findAll();
    }

    public void save(Request request) {
        repository.save(request);
    }

    public Request find(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
