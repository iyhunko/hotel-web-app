package com.iyhunko.hotel.repositories;

import com.iyhunko.hotel.models.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
