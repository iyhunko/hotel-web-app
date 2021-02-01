package com.iyhunko.hotel.services;

import com.iyhunko.hotel.models.Room;
import com.iyhunko.hotel.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository repository;

    public List<Room> all() {
        return (List<Room>) repository.findAll();
    }

    public void save(Room room) {
        repository.save(room);
    }

    public Room find(Long id) {
        return repository.findById(id).get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
