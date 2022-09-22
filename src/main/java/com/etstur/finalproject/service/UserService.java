package com.etstur.finalproject.service;

import com.etstur.finalproject.dao.Dao;
import com.etstur.finalproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {



    private final Dao<User> userDao;

    public UserService(Dao<User> userDao) {
        this.userDao = userDao;
    }

    public void save(User user){
        userDao.save(user);
    }

    public Collection<User> getAll(){
        return userDao.getAll();
    }

    public Optional<User> getById(long id){
        return userDao.get(id);
    }

    public void update(User user){
        userDao.update(user);
    }
    public void delete(User user){
        userDao.delete(user);
    }


}
