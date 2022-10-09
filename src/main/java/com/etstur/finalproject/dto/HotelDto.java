package com.etstur.finalproject.dto;

import com.etstur.finalproject.entity.Room;
import lombok.*;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class HotelDto {
    private String name;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String email;
    private String description;
    private String image;
    private List<Room> rooms;
}
