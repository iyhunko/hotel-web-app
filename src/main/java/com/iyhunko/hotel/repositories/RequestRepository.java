package com.iyhunko.hotel.repositories;

import com.iyhunko.hotel.models.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
