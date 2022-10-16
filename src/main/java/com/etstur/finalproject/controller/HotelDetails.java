package com.etstur.finalproject.controller;

import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.entity.Room;
import com.etstur.finalproject.service.RoomService;
import com.etstur.finalproject.temp.CurrentReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
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
        model.addAttribute("hotel", hotel);
        model.addAttribute("rooms", getRooms(hotel.getId()));
        model.addAttribute("myRoom", new Room());
        return "hotels/roomList";
    }


    @PostMapping("/purchase")
    public String purchase(@ModelAttribute("myRoom") Room room, Model model) throws ParseException {
        CurrentReservation.setRoom(room.getTitle());
        CurrentReservation.setPrice((int) room.getPrice());

        model.addAttribute("room", room);
        model.addAttribute("arrivalDate",CurrentReservation.getArrivalDate());
        model.addAttribute("child",CurrentReservation.getChildren());
        model.addAttribute("destination",CurrentReservation.getDestination());
        model.addAttribute("persons",CurrentReservation.getPersons());
        model.addAttribute("price",CurrentReservation.getPrice());
        model.addAttribute("roomTitle",CurrentReservation.getRoom());
        model.addAttribute("user",getUsername());

        return "hotels/purchase";
    }


    private List<Room> getRooms(Long hotelId) {
        return roomService.findAllByHotelId(hotelId);
    }

    private String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}
