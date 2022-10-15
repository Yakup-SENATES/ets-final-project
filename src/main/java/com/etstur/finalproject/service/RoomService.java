package com.etstur.finalproject.service;


import com.etstur.finalproject.entity.Room;

import java.util.List;

public interface RoomService {

    List<Room> findAllByHotelId(Long hotelId);

}
