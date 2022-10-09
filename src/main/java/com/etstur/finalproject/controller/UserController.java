package com.etstur.finalproject.controller;


import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //register page Controller
    @PostMapping("/process_register")
    public String processRegister(CurrentUser user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }

    //redirect to register
    @GetMapping("/register")
    public String register(Model model ){
        model.addAttribute("user", new CurrentUser());
        return "register";
    }

}
