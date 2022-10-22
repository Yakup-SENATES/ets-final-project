package com.etstur.finalproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.etstur.finalproject.dto.HotelDto;
import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HotelServiceImpl.class})
@ExtendWith(SpringExtension.class)
class HotelServiceImplTest {

    @MockBean
    private HotelRepository hotelRepository;

    @Autowired
    private HotelServiceImpl hotelServiceImpl;

    /**
     * Method under test: {@link HotelServiceImpl#getAllCities()}
     */
    @Test
    void testGetAllCities() {
        ArrayList<String> stringList = new ArrayList<>();
        when(hotelRepository.findAllHotelCities()).thenReturn(stringList);
        List<String> actualAllCities = hotelServiceImpl.getAllCities();
        assertSame(stringList, actualAllCities);
        assertTrue(actualAllCities.isEmpty());
        verify(hotelRepository).findAllHotelCities();
    }

    /**
     * Method under test: {@link HotelServiceImpl#getHotelsWithDestination(String)}
     */
    @Test
    void testGetHotelsWithDestination_whenDestinationProvided_andDestinationExist() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        when(hotelRepository.findAllByCity(any())).thenReturn(hotelList);
        List<Hotel> actualHotelsWithDestination = hotelServiceImpl.getHotelsWithDestination("Destination");
        assertSame(hotelList, actualHotelsWithDestination);
        assertTrue(actualHotelsWithDestination.isEmpty());
        verify(hotelRepository).findAllByCity(any());
    }

    /**
     * Method under test: {@link HotelServiceImpl#getHotelsWithDestination(String)}
     * hotel front end tarafýnda boþ olmayacak þekilde kontrol edilmektedir ayrýca
     * destination lar veritabanýndan gelmekte olduðudan dolayý destination boþ olamaz
     */
    @Test
    void testGetHotelsWithDestination_whenDestinationProvided_andDestinationNotExist() {
        when(hotelRepository.findAllByCity(any())).thenReturn(new ArrayList<>());
        List<Hotel> actualHotelsWithDestination = hotelServiceImpl.getHotelsWithDestination("Destination");
        assertTrue(actualHotelsWithDestination.isEmpty());
        verify(hotelRepository).findAllByCity(any());
    }


    /**
     * Method under test: {@link HotelServiceImpl#getHotelDetails(long)}
     */
    @Test
    void testGetHotelDetails() {
        Hotel hotel = new Hotel();
        hotel.setAddress("42 Main St");
        hotel.setCity("Oxford");
        hotel.setCountry("GB");
        hotel.setDescription("The characteristics of someone or something");
        hotel.setEmail("jane.doe@example.org");
        hotel.setId(123L);
        hotel.setImage("Image");
        hotel.setName("Name");
        hotel.setPhone("4105551212");
        hotel.setRooms(new ArrayList<>());
        Optional<Hotel> ofResult = Optional.of(hotel);
        when(hotelRepository.findById((Long) any())).thenReturn(ofResult);
        HotelDto actualHotelDetails = hotelServiceImpl.getHotelDetails(123L);
        assertEquals("42 Main St", actualHotelDetails.getAddress());
        assertTrue(actualHotelDetails.getRooms().isEmpty());
        assertEquals("4105551212", actualHotelDetails.getPhone());
        assertEquals("Name", actualHotelDetails.getName());
        assertEquals("Image", actualHotelDetails.getImage());
        assertEquals("jane.doe@example.org", actualHotelDetails.getEmail());
        assertEquals("The characteristics of someone or something", actualHotelDetails.getDescription());
        assertEquals("GB", actualHotelDetails.getCountry());
        assertEquals("Oxford", actualHotelDetails.getCity());
        verify(hotelRepository).findById((Long) any());
    }


}

