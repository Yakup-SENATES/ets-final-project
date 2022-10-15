package com.etstur.finalproject.controller;

import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.entity.Room;
import com.etstur.finalproject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/hotelDetails")
@RequiredArgsConstructor
public class HotelDetails {

    private final RoomService roomService;

    @RequestMapping("/detail")
    public String detail(
            @ModelAttribute("myHotel") Hotel hotel,
            Model model
    ) {
        System.out.println(hotel);
        System.out.println(getRooms(hotel.getId()));
        model.addAttribute("hotel", hotel);
       // model.addAttribute("rooms", getRooms(hotel.getId()));
        return "hotels/purchase";
    }

    private List<Room> getRooms(Long hotelId) {
        return roomService.findAllByHotelId(hotelId);
    }
}