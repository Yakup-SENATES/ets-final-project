package com.etstur.finalproject.service.impl;

import com.etstur.finalproject.repository.HotelRepository;
import com.etstur.finalproject.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<String> getAllCities() {
        return hotelRepository.findAllHotelCities();
    }
}