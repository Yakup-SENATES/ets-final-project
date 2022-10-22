package com.etstur.finalproject.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.etstur.finalproject.entity.Hotel;
import com.etstur.finalproject.entity.Room;
import com.etstur.finalproject.service.RoomService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {HotelDetails.class})
@ExtendWith(SpringExtension.class)
class HotelDetailsTest {
    @Autowired
    private HotelDetails hotelDetails;

    @MockBean
    private RoomService roomService;

    /**
     * Method under test: {@link HotelDetails#detail(Hotel, Model)}
     */
    @Test
    void testDetail() throws Exception {
        when(roomService.findAllByHotelId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/hotelDetails/detail");
        MockMvcBuilders.standaloneSetup(hotelDetails)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(4))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hotel", "myHotel", "myRoom", "rooms"))
                .andExpect(MockMvcResultMatchers.view().name("hotels/roomList"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("hotels/roomList"));
    }

    /**
     * Method under test: {@link HotelDetails#detail(Hotel, Model)}
     */
    @Test
    void testDetail2() throws Exception {
        when(roomService.findAllByHotelId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/hotelDetails/detail");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(hotelDetails)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(4))
                .andExpect(MockMvcResultMatchers.model().attributeExists("hotel", "myHotel", "myRoom", "rooms"))
                .andExpect(MockMvcResultMatchers.view().name("hotels/roomList"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("hotels/roomList"));
    }

    /**
     * Method under test: {@link HotelDetails#purchase(Room, Model)}
     */
    @Test
    void testPurchase() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(hotelDetails).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

