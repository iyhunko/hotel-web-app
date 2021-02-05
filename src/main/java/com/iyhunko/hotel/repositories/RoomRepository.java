package com.iyhunko.hotel.repositories;

import com.iyhunko.hotel.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
