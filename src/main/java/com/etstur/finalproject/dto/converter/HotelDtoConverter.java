package com.etstur.finalproject.dto.converter;

import com.etstur.finalproject.dto.HotelDto;
import com.etstur.finalproject.entity.Hotel;

import java.util.Optional;

public class HotelDtoConverter {

    public HotelDto convert(Optional<Hotel> hotel) {

        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.get().getName());
        hotelDto.setAddress(hotel.get().getAddress());
        hotelDto.setCity(hotel.get().getCity());
        hotelDto.setCountry(hotel.get().getCountry());
        hotelDto.setPhone(hotel.get().getPhone());
        hotelDto.setRooms(hotel.get().getRooms());
        hotelDto.setEmail(hotel.get().getEmail());
        hotelDto.setDescription(hotel.get().getDescription());
        hotelDto.setImage(hotel.get().getImage());
        return hotelDto;
    }

}
