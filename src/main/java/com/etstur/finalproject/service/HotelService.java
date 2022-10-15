package com.etstur.finalproject.service;


import com.etstur.finalproject.dto.HotelDto;
import com.etstur.finalproject.entity.Hotel;


import java.util.List;

public interface HotelService {

    List<String> getAllCities();
    List<Hotel> getHotelsWithDestination(String destination);

    HotelDto getHotelDetails(long hotelId);

}
