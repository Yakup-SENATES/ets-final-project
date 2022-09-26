package com.etstur.finalproject.dao;

import com.etstur.finalproject.entity.User;

public interface UserDao {

    public User findUserByEmail(String email);
    public User findUserByUserName(String userName);
    void saveOrUpdateUser(User user);
}
