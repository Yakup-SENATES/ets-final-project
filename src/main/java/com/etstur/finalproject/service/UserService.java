package com.etstur.finalproject.service;


import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.temp.CurrentUser;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    public User findUserByEmail(String email);
    public long getLoggedUserId();
    public void saveUser(CurrentUser currentUser);


    User findByEmail(String username);
}
