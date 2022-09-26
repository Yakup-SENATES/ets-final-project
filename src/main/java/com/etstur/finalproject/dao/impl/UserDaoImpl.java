package com.etstur.finalproject.dao.impl;

import com.etstur.finalproject.dao.UserDao;
import com.etstur.finalproject.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


// Dao Pattern for User
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    //dao pattern to deal with retrieve and send data to and from database for user

    //inject entity manager for constructor injection
    private final EntityManager entityManager;


    //get current hibernate session
    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    //find user by email
    @Override
    public User findUserByEmail(String email) {
        return getSession().createQuery("from User where email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();

    }

    //get user by username
    @Override
    public User findUserByUserName(String userName) {

        return getSession().createQuery("from User where userName = :userName", User.class)
                .setParameter("userName", userName)
                .getSingleResult();
    }

    //save or update user
    @Override
    public void saveOrUpdateUser(User user) {
        getSession().saveOrUpdate(user);
    }
}
