package com.etstur.finalproject.dao;

import com.etstur.finalproject.entity.Reservation;

import java.util.Collection;

public interface ReservationDao {

    public Reservation getReservationForLoggedUserById(int resId);

    public Collection<Reservation> getReservationsUserById(int userId);

    public void saveOrUpdateReservation(Reservation reservation);

    public void deleteReservation(Reservation reservation);

}
