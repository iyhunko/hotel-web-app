package com.iyhunko.hotel.services;

import com.iyhunko.hotel.models.Room;
import com.iyhunko.hotel.repositories.RoomRepository;
import com.iyhunko.hotel.specifications.RoomSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Page<Room> getWithPagination(int pageNo, int pageSize, String sortBy, String sortOrder, Map<String,String> requestParams) {

        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Specification spec = Specification.where(
                RoomSpecification.classEquals(requestParams.get("roomClass"))
        ).and(
                RoomSpecification.statusEquals(requestParams.get("status"))
        ).and(
                RoomSpecification.roomsQuantityEquals(
                        (requestParams.get("roomsQuantity") != null && !requestParams.get("roomsQuantity").trim().isEmpty())
                                ? Integer.valueOf(requestParams.get("roomsQuantity"))
                                : null
                )
        ).and(
                RoomSpecification.priceGreaterThan(
                        (requestParams.get("priceFrom") != null && !requestParams.get("priceFrom").trim().isEmpty())
                                ? Integer.valueOf(requestParams.get("priceFrom"))
                                : null
                )
        ).and(
                RoomSpecification.priceLessThan(
                        (requestParams.get("priceTo") != null && !requestParams.get("priceTo").trim().isEmpty())
                                ? Integer.valueOf(requestParams.get("priceTo"))
                                : null
                )
        );

        return repository.findAll(spec, pageable);
    }
}
