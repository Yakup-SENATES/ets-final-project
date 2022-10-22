package com.etstur.finalproject.controller;

import com.etstur.finalproject.service.HotelService;
import com.etstur.finalproject.service.ReservationService;
import com.etstur.finalproject.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {HotelController.class})
@ExtendWith(SpringExtension.class)
class HotelControllerTest {
    @Autowired
    private HotelController hotelController;

    @MockBean
    private HotelService hotelService;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link HotelController#list(String, String, String, int, int, int, Model)}
     */
    @Test
    void testList() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/hotels/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("adults", String.valueOf(1))
                .param("checkIn", "foo")
                .param("checkOut", "foo");
        MockHttpServletRequestBuilder paramResult1 = paramResult.param("children", String.valueOf(1))
                .param("destination", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult1.param("roomCount", String.valueOf(1));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(hotelController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

