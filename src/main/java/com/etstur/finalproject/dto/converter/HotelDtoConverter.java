package com.etstur.finalproject.dto.converter;

import com.etstur.finalproject.dto.HotelDto;
import com.etstur.finalproject.entity.Hotel;

public class HotelDtoConverter {

    public HotelDto convert(Hotel hotel) {

        HotelDto hotelDto = new HotelDto();
        hotelDto.setName(hotel.getName());
        hotelDto.setAddress(hotel.getAddress());
        hotelDto.setCity(hotel.getCity());
        hotelDto.setCountry(hotel.getCountry());
        hotelDto.setPhone(hotel.getPhone());
        hotelDto.setRooms(hotel.getRooms());
        hotelDto.setEmail(hotel.getEmail());
        hotelDto.setDescription(hotel.getDescription());
        hotelDto.setImage(hotel.getImage());
        return hotelDto;
    }

}
