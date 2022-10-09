package com.etstur.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    @RequestMapping("/list")
    public String list(Model model) {

        return "hotels/list";
    }
}
