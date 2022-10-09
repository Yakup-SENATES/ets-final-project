package com.etstur.finalproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RoomDto {

    private String description;
    private String image;
    private double price;
}
