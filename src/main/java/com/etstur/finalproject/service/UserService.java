package com.etstur.finalproject.service;


import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.temp.CurrentUser;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User findUserByEmail(String email);
    long getLoggedUserId();
    void saveUser(CurrentUser currentUser);

}
