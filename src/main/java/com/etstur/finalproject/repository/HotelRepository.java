package com.etstur.finalproject.repository;

import com.etstur.finalproject.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select city from Hotel")
    public List<String> findAllHotelCities();
}
