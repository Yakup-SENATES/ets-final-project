package com.etstur.finalproject.controller;

import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.service.HotelService;
import com.etstur.finalproject.service.ReservationService;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentReservation;
import com.etstur.finalproject.temp.CurrentUser;
import io.swagger.annotations.ApiOperation;
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
import java.text.ParseException;
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
    public ModelAndView homePage(Model model) {
        model.addAttribute("currentUser", new CurrentUser());
        model.addAttribute("currentReservation", new CurrentReservation());
        return new ModelAndView("home-page", getHotelCities());
    }



    // Booking page
    @GetMapping("/new-reservation")
    public String newReservation(Model model) {
        model.addAttribute("hotel", new CurrentReservation());
        model.addAttribute("newRes", new CurrentUser());
        return "new-reservation";
    }

    //save new Reservation
    @PostMapping("/saveReservation")
    public String saveReservation() throws ParseException {

        reservationService.saveOrUpdateReservation();

        return "redirect:/congratulation";
    }


    // reservations of User
    @GetMapping("/your-reservations")
    public String yourReservations(Model model) {
        model.addAttribute("resList", reservationService.getAllReservationsForLoggedUser());
        return "your-reservations";
    }

    // update reservation
    @GetMapping("/reservation-edit")
    public String updateReservation(@RequestParam("resId") int resId, Model model) {
        //new Update reservation attribute send to  service to store it
        model.addAttribute("newRes", reservationService.reservationToCurrentReservation((long) resId));
        return "edit-reservation";
    }


    // Delete Reservation
    @RequestMapping("/reservation-delete")
    @ApiOperation(value = "Delete Reservation")
    public String deleteReservation(@RequestParam(value = "resId") long resId) {
        reservationService.deleteReservation(resId);
        return "redirect:/";
    }



    @GetMapping("/congratulation")
    public String congratulation() {
        return "congratulation";
    }
}
