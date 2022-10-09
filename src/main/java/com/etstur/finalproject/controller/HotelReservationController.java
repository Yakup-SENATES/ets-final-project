package com.etstur.finalproject.controller;

import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.service.HotelService;
import com.etstur.finalproject.service.ReservationService;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentReservation;
import com.etstur.finalproject.temp.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class HotelReservationController {

    private final UserService userService;
    private final ReservationService reservationService;
    private final HotelService hotelService;

    // initbinder processes all web requests and trims all strings
    // and its provide us to handle null values
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, trimmerEditor);
    }

    private Map<String, Object> getHotelCities() {
        return Map.of("cities", hotelService.getAllCities());
    }

    //home page
    @GetMapping
    public ModelAndView homePage() {
        return new ModelAndView("home-page", getHotelCities());
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

    // registration process page
    @PostMapping("/processRegistration")
    public String registrationProcess(@Valid @ModelAttribute("newUser") CurrentUser currentUser,
                                      BindingResult bindingResult, Model model) {

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
        return "redirect:/login-form-page";
    }

    // Booking page
    @GetMapping("/new-reservation")
    public String newReservation(Model model) {
        model.addAttribute("hotel", new CurrentReservation());
        model.addAttribute("newRes", new CurrentUser());
        return "new-reservation";
    }

    //save new Reservation
    @PostMapping("/proceed-reservation")
    public String proceedReservation(@Valid @ModelAttribute("newRes") CurrentReservation currentReservation,
                                     BindingResult bindingResult, Model model) {

        // if there is no error than save reservation
        if (bindingResult.hasErrors()) {
            model.addAttribute("newRes", new CurrentReservation());
            model.addAttribute("reservationError", "Please fill all fields");
        } else {
            reservationService.saveOrUpdateReservation(currentReservation);
            model.addAttribute("reservationSuccess", "Reservation successful");
        }
        return "redirect:/your-reservations";
    }

    // reservations of User
    @GetMapping("/your-reservations")
    public String yourReservations(Model model) {
        model.addAttribute("resList", reservationService.getAllReservationsForLoggedUser());
        return "your-reservations";
    }

    // update reservation
    @GetMapping("/reservation-update")
    public String updateReservation(@RequestParam("resId") int resId, Model model) {
        //new Update reservation attribute send to  service to store it
        model.addAttribute("newRes", reservationService.reservationToCurrentReservation((long) resId));
        return "reservation-update";
    }


    // Delete Reservation
    @GetMapping("/reservation-delete")
    public String deleteReservation(@RequestParam("resId") int resId) {
        reservationService.deleteReservation((long) resId);
        return "redirect:/your-reservations";
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


}
