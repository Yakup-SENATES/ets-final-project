package com.etstur.finalproject.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private String image;

    private double price;

}
