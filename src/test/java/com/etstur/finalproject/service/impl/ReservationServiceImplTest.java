package com.etstur.finalproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.etstur.finalproject.entity.Reservation;
import com.etstur.finalproject.repository.ReservationRepository;
import com.etstur.finalproject.service.UserService;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReservationServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ReservationServiceImplTest {
    @MockBean
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link ReservationServiceImpl#getReservationForLoggedUserById(Long)}
     */
    @Test
    void testGetReservationForLoggedUserById() {
        Reservation reservation = new Reservation();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        reservation.setArrivalDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        reservation.setChildren(1);
        reservation.setDestination("Destination");
        reservation.setId(123L);
        reservation.setOpenBuffet("Open Buffet");
        reservation.setPersons(1);
        reservation.setPrice(10.0d);
        reservation.setRoom("Room");
        reservation.setRooms(1000);
        reservation.setStayDays(1);
        reservation.setUserId(123L);
        Optional<Reservation> ofResult = Optional.of(reservation);
        when(reservationRepository.findById((Long) any())).thenReturn(ofResult);
        Optional<Reservation> actualReservationForLoggedUserById = reservationServiceImpl
                .getReservationForLoggedUserById(123L);
        assertSame(ofResult, actualReservationForLoggedUserById);
        assertTrue(actualReservationForLoggedUserById.isPresent());
        verify(reservationRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReservationServiceImpl#getAllReservationsForLoggedUser()}
     */
    @Test
    void testGetAllReservationsForLoggedUser() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        when(reservationRepository.findAllByUserId((Long) any())).thenReturn(reservationList);
        Collection<Reservation> actualAllReservationsForLoggedUser = reservationServiceImpl
                .getAllReservationsForLoggedUser();
        assertSame(reservationList, actualAllReservationsForLoggedUser);
        assertTrue(actualAllReservationsForLoggedUser.isEmpty());
        verify(reservationRepository).findAllByUserId((Long) any());
    }

    /**
     * Method under test: {@link ReservationServiceImpl#saveOrUpdateReservation()}
     */
    @Test
    void testSaveOrUpdateReservation() throws ParseException {
        Reservation reservation = new Reservation();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        reservation.setArrivalDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        reservation.setChildren(1);
        reservation.setDestination("Destination");
        reservation.setId(123L);
        reservation.setOpenBuffet("Open Buffet");
        reservation.setPersons(1);
        reservation.setPrice(10.0d);
        reservation.setRoom("Room");
        reservation.setRooms(1000);
        reservation.setStayDays(1);
        reservation.setUserId(123L);
        when(reservationRepository.save((Reservation) any())).thenReturn(reservation);
        reservationServiceImpl.saveOrUpdateReservation();
        verify(reservationRepository).save((Reservation) any());
    }

    /**
     * Method under test: {@link ReservationServiceImpl#reservationToCurrentReservation(Long)}
     */
    @Test
    void testReservationToCurrentReservation() {
        Reservation reservation = new Reservation();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        reservation.setArrivalDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        reservation.setChildren(1);
        reservation.setDestination("Destination");
        reservation.setId(123L);
        reservation.setOpenBuffet("Open Buffet");
        reservation.setPersons(1);
        reservation.setPrice(10.0d);
        reservation.setRoom("Room");
        reservation.setRooms(1000);
        reservation.setStayDays(1);
        reservation.setUserId(123L);
        Optional<Reservation> ofResult = Optional.of(reservation);
        when(reservationRepository.findById((Long) any())).thenReturn(ofResult);
        reservationServiceImpl.reservationToCurrentReservation(123L);
        verify(reservationRepository).findById((Long) any());
        assertTrue(reservationServiceImpl.getAllReservationsForLoggedUser().isEmpty());
    }


    /**
     * Method under test: {@link ReservationServiceImpl#deleteReservation(Long)}
     */
    @Test
    void testDeleteReservation() {
        doNothing().when(reservationRepository).deleteById((Long) any());
        reservationServiceImpl.deleteReservation(123L);
        verify(reservationRepository).deleteById((Long) any());
    }
}

