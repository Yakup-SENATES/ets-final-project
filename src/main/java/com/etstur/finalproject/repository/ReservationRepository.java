package com.etstur.finalproject.repository;

import com.etstur.finalproject.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findById(Long id);

    Collection<Reservation> findAllByUserId(Long userId);

}
