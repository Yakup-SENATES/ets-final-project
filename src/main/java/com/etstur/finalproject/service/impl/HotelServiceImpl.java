package com.etstur.finalproject.service.impl;

import com.etstur.finalproject.dto.HotelDto;
import com.etstur.finalproject.dto.converter.HotelDtoConverter;
import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.repository.HotelRepository;
import com.etstur.finalproject.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    private HotelDtoConverter converter = new HotelDtoConverter();

    @Override
    public List<String> getAllCities() {
        return hotelRepository.findAllHotelCities();
    }

    @Override
    public List<Hotel> getHotelsWithDestination(String destination) {
        return hotelRepository.findAllByCity(destination);
    }

    @Override
    public HotelDto getHotelDetails(long hotelId) {
        return converter.convert(hotelRepository.findById(hotelId));
    }


}
