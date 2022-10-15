package com.etstur.finalproject.repository;

import com.etstur.finalproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByHotelId(Long hotelId);

}
