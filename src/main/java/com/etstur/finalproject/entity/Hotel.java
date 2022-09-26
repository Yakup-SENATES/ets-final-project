package com.etstur.finalproject.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String email;
    private String description;
    private String image;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;


}
