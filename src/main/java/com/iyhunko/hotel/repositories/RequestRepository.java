package com.iyhunko.hotel.repositories;

import com.iyhunko.hotel.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
