package com.etstur.finalproject.service;


import com.etstur.finalproject.entity.Reservation;
import com.etstur.finalproject.temp.CurrentReservation;

import java.text.ParseException;
import java.util.Collection;
import java.util.Optional;

public interface ReservationService {

    public Optional<Reservation> getReservationForLoggedUserById(Long id) ;
    public Collection<Reservation> getAllReservationsForLoggedUser();
    public void saveOrUpdateReservation(CurrentReservation currentReservation) throws ParseException;
    public void deleteReservation(Long id);
    public CurrentReservation reservationToCurrentReservation(Long resId);
}
