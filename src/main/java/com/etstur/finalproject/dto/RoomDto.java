package com.etstur.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomDto {

    private String description;
    private String image;
    private double price;

}
