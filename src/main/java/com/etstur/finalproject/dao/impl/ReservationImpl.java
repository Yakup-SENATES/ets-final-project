package com.etstur.finalproject.dao.impl;

import com.etstur.finalproject.dao.ReservationDao;
import com.etstur.finalproject.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class ReservationImpl implements ReservationDao {

    private final EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public Reservation getReservationForLoggedUserById(int resId) {
        //Create a query with HQL to get user
        return getSession().createQuery("from Reservation where id=:resId", Reservation.class)
                .setParameter("resId", resId)
                .getSingleResult();

    }

    @Override
    public Collection<Reservation> getReservationsUserById(int userId) {
        return getSession().createQuery("from Reservation where userId=:userId", Reservation.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void saveOrUpdateReservation(Reservation reservation) {
        getSession().saveOrUpdate(reservation);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        getSession().delete(reservation);
    }

}
