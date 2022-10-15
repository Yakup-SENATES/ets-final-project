package com.etstur.finalproject.service.impl;

import com.etstur.finalproject.entity.Room;
import com.etstur.finalproject.repository.RoomRepository;
import com.etstur.finalproject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> findAllByHotelId(Long hotelId) {
        return roomRepository.findAllByHotelId(hotelId);
    }
}
