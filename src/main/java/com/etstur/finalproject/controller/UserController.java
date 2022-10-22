package com.etstur.finalproject.controller;


import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //register page Controller
    @PostMapping("/process_register")
    public String processRegister(CurrentUser currentUser, BindingResult bindingResult, Model model) {
        //check if user already exists
        User user = userService.findUserByEmail(currentUser.getEmail());
        if (user != null) {
            model.addAttribute("registrationError", "User already exists");
            model.addAttribute("newUser", new CurrentUser());
            return "login";
        }

        //if binding has no error than create a User
        if (bindingResult.hasErrors()) {
            model.addAttribute("newUser", new CurrentUser());
            model.addAttribute("registrationError", "Please fill all fields");
        } else {
            userService.saveUser(currentUser);
            model.addAttribute("registrationSuccess", "Registration successful");
        }
        return "register_success";
    }


    //redirect to register
    @GetMapping("/register")
    public String register(Model model ){
        model.addAttribute("user", new CurrentUser());
        return "register";
    }

    //logout page
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        //handle logout for logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login-form-page?logout";
    }


    //login page
    @GetMapping("/login-form-page")
    public String loginPage(Model model) {
        //if user is already logged in, redirect to home page
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || authentication instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        // new user Attribute for signup form
        model.addAttribute("newUser", new User());
        return "login";
    }

}
