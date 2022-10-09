package com.etstur.finalproject.dto;


import com.etstur.finalproject.entity.Reservation;
import com.etstur.finalproject.entity.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDto {

    private String userName;
    private String password;
    private String email;
    private Collection<Role> roles;
    private Collection<Reservation> reservations;
}
