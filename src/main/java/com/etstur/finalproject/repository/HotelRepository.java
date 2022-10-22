package com.etstur.finalproject.repository;

import com.etstur.finalproject.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select distinct(city) from Hotel order by city")
    List<String> findAllHotelCities();

    @Query("SELECT h from Hotel h where h.city = ?1")
    List<Hotel> findAllByCity(String destination);

}
