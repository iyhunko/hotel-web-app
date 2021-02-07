package com.iyhunko.hotel.repositories;

import com.iyhunko.hotel.models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    @Query("SELECT r FROM Room r WHERE r.roomClass LIKE %?1%")
    public Page<Room> getByClass(String roomClass, Pageable pageable);
}
