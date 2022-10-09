package com.etstur.finalproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
public class ReservationDto {
    private String room;
    private double price;
    private int rooms;
    private int persons;
    private int children;
    private String openBuffet;
    private Date arrivalDate;
    private int stayDays;
    private Long userId;
}
