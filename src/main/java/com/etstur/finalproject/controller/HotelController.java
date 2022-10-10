package com.etstur.finalproject.controller;

import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.temp.CurrentReservation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @RequestMapping("/list")
    public String list(
            @RequestParam(value = "destination") String destination,
            @RequestParam(value = "checkIn") String checkIn,
            @RequestParam(value = "checkOut") String checkOut,
            @RequestParam(value = "adultCount") int adults,
            @RequestParam(value = "childCount") int children,
            Model model) {
        Hotel hotel = new Hotel();

        return "hotels/list";
    }
}
