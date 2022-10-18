package com.etstur.finalproject.service.impl;

import com.etstur.finalproject.entity.Reservation;
import com.etstur.finalproject.repository.ReservationRepository;
import com.etstur.finalproject.service.ReservationService;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentReservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserService userService;

    //get reservation for Logged User
    @Override
    @Transactional
    public Optional<Reservation> getReservationForLoggedUserById(Long id) {
        return reservationRepository.findById(id);
    }

    //get all reservations for logged user
    @Override
    @Transactional
    public Collection<Reservation> getAllReservationsForLoggedUser() {
        return reservationRepository.findAllByUserId(userService.getLoggedUserId());
    }

    //transfer data between temp reservation and
    //Reservation class after check it to save it
    @Override
    @Transactional
    public void saveOrUpdateReservation() throws ParseException {
        Reservation reservation = new Reservation();

        reservation.setUserId((long) CurrentReservation.getUserTId());
        reservation.setArrivalDate(CurrentReservation.getArrivalDate());
        reservation.setOpenBuffet(CurrentReservation.getOpenBuffet());
        reservation.setStayDays(CurrentReservation.getStayPeriod());
        reservation.setChildren(CurrentReservation.getChildren());
        reservation.setPersons(CurrentReservation.getPersons());
        reservation.setPrice(CurrentReservation.getPrice());
        reservation.setRooms(CurrentReservation.getRooms());
        reservation.setRoom(CurrentReservation.getRoom());
        reservation.setId(CurrentReservation.getId());

        reservationRepository.save(reservation);
    }

    @Override
    public CurrentReservation reservationToCurrentReservation(Long resId) {
        Optional<Reservation> reservation = getReservationForLoggedUserById(resId);
        CurrentReservation currentReservation = new CurrentReservation();

        CurrentReservation.setArrivalDate(reservation.get().getArrivalDate());
        CurrentReservation.setOpenBuffet(reservation.get().getOpenBuffet());
        CurrentReservation.setStayPeriod(reservation.get().getStayDays());
        CurrentReservation.setChildren(reservation.get().getChildren());
        CurrentReservation.setPersons(reservation.get().getPersons());
        CurrentReservation.setUserTId(Math.toIntExact(reservation.get().getUserId()));
        CurrentReservation.setRooms(reservation.get().getRooms());
        CurrentReservation.setRoom(reservation.get().getRoom());
        CurrentReservation.setPrice((int) reservation.get().getPrice());
        CurrentReservation.setId(reservation.get().getId());

        return currentReservation;
    }
    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
