package com.etstur.finalproject.dto.converter;

import com.etstur.finalproject.dto.RoomDto;
import com.etstur.finalproject.entity.Room;

public class RoomDtoConverter {

    public static RoomDto convert(Room room) {
        return RoomDto.builder()
                .description(room.getDescription())
                .image(room.getImage())
                .price(room.getPrice())
                .build();
    }
}
