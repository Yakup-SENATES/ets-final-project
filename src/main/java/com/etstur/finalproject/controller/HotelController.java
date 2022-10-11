package com.etstur.finalproject.controller;

import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.service.HotelService;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final UserService userService;
    private final HotelService hotelService;

    @GetMapping("/list")
    public String list(
            @RequestParam(value = "destination") String destination,
            @RequestParam(value = "checkIn") String checkIn,
            @RequestParam(value = "checkOut") String checkOut,
            @RequestParam(value = "adultCount") int adults,
            @RequestParam(value = "childCount") int children,
            Model model) throws ParseException, IOException {
        CurrentReservation reservation =  CurrentReservation
                .builder()
                .destination(destination)
                .children(children)
                .persons(adults)
                .arrivalDate(parseDate(checkIn))
                .openBuffet("false")
                .userTId(Math.toIntExact(loggedUserId()))
                .stayPeriod(betweenDates(parseDate(checkIn), parseDate(checkOut)))
                .build();
        model.addAttribute("reservation", reservation);
        model.addAttribute("hotels", getHotels(destination));
        return "hotels/list";
    }

    private Long loggedUserId() {
          Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                return userService.findUserByEmail(((UserDetails) principal).getUsername()).getId();
            }
            return null;
    }

    //Calculate the number of days between two dates
    private int betweenDates(Date firstDate, Date secondDate) throws IOException
    {
        return (int) ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
    }
    //Parse date from string
    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("dd-MM-yyyy").parse(date);
    }

    private List<Hotel> getHotels(String destination) {
        return hotelService.getHotelsWithDestination(destination);
    }


}
