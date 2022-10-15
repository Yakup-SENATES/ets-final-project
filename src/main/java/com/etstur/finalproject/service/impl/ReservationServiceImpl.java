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
    public void saveOrUpdateReservation(CurrentReservation currentReservation) throws ParseException {
        Reservation reservation = new Reservation();

        reservation.setUserId(userService.getLoggedUserId());

        reservation.setArrivalDate(currentReservation.getArrivalDate());
        reservation.setOpenBuffet(currentReservation.getOpenBuffet());
        reservation.setStayDays(currentReservation.getStayPeriod());
        reservation.setChildren(currentReservation.getChildren());
        reservation.setPersons(currentReservation.getPersons());
        reservation.setPrice(currentReservation.getPrice());
        reservation.setRooms(currentReservation.getRooms());
        reservation.setRoom(currentReservation.getRoom());
        reservation.setId(currentReservation.getId());

        reservationRepository.save(reservation);
    }

    @Override
    public CurrentReservation reservationToCurrentReservation(Long resId) {
        Optional<Reservation> reservation = getReservationForLoggedUserById(resId);
        CurrentReservation currentReservation = new CurrentReservation();

        currentReservation.setArrivalDate(reservation.get().getArrivalDate());
        currentReservation.setOpenBuffet(reservation.get().getOpenBuffet());
        currentReservation.setStayPeriod(reservation.get().getStayDays());
        currentReservation.setChildren(reservation.get().getChildren());
        currentReservation.setPersons(reservation.get().getPersons());
        currentReservation.setUserTId(Math.toIntExact(reservation.get().getUserId()));
        currentReservation.setRooms(reservation.get().getRooms());
        currentReservation.setRoom(reservation.get().getRoom());
        currentReservation.setPrice((int) reservation.get().getPrice());
        currentReservation.setId(reservation.get().getId());

        return currentReservation;
    }
    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
