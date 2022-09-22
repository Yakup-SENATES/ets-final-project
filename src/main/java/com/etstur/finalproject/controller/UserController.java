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

    @GetMapping("/all")
    public Collection<User> getAllUser(){
        return userService.getAll();
    }

    @PostMapping("/save")
    public void saveUser(@RequestBody User user){
        userService.save(user);
    }

}
