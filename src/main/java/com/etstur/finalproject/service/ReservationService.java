package com.etstur.finalproject.service;


import com.etstur.finalproject.entity.Reservation;
import com.etstur.finalproject.temp.CurrentReservation;

import java.text.ParseException;
import java.util.Collection;
import java.util.Optional;

public interface ReservationService {

    Optional<Reservation> getReservationForLoggedUserById(Long id);

    Collection<Reservation> getAllReservationsForLoggedUser();

    void saveOrUpdateReservation() throws ParseException;

    void deleteReservation(Long id);

    CurrentReservation reservationToCurrentReservation(Long resId);
}
