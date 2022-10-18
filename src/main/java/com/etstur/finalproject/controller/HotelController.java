package com.etstur.finalproject.controller;

import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.service.HotelService;
import com.etstur.finalproject.service.ReservationService;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final UserService userService;
    private final HotelService hotelService;
    private final ReservationService reservationService;
    /*
     * search edilince çalışır ve arama sonuçlarını gösterir
     * */

    @GetMapping("/list")
    public String list(
            @NonNull @RequestParam(value = "destination") String destination,
            @NonNull @RequestParam(value = "checkIn") String checkIn,
            @NonNull @RequestParam(value = "checkOut") String checkOut,
            @NonNull @RequestParam(value = "adultCount") int adults,
            @NonNull @RequestParam(value = "roomCount") int roomCount,
            @RequestParam(value = "childCount") int children,
            Model model) throws ParseException, IOException {


        setReservation(destination, checkIn, checkOut, adults, children,roomCount);
        model.addAttribute("myHotel", new Hotel());
        model.addAttribute("hotels", getHotels(destination));
        return "hotels/list";
    }




    private void setReservation(String destination, String checkIn,
                                String checkOut, int adults,
                                int children,int roomCount) throws ParseException, IOException {
        CurrentReservation.setRooms(roomCount);
        CurrentReservation.setDestination(destination);
        CurrentReservation.setChildren(children);
        CurrentReservation.setPersons(adults);
        CurrentReservation.setArrivalDate(parseDate(checkIn));
        CurrentReservation.setOpenBuffet("false");
        CurrentReservation.setUserTId(Math.toIntExact(loggedUserId()));
        CurrentReservation.setStayPeriod(betweenDates(parseDate(checkIn), parseDate(checkOut)));
        CurrentReservation.setUserTId(Math.toIntExact(loggedUserId()));
    }

    @NonNull
    private Long loggedUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return userService.findUserByEmail(((UserDetails) principal).getUsername()).getId();
        }
        return 0L;
    }

    //Calculate the number of days between two dates
    private int betweenDates(Date firstDate, Date secondDate) throws IOException {
        return (int) ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());
    }

    //Parse date from string
    private Date parseDate(String date) throws ParseException {
        return new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH).parse(date);
    }

    private List<Hotel> getHotels(String destination) {
        return hotelService.getHotelsWithDestination(destination);
    }


}
