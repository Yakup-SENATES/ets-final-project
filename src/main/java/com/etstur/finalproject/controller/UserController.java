package com.etstur.finalproject.controller;

import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



}
