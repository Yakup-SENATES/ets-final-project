package com.etstur.finalproject.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.etstur.finalproject.service.HotelService;
import com.etstur.finalproject.service.ReservationService;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentReservation;

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

@ContextConfiguration(classes = {HotelReservationController.class})
@ExtendWith(SpringExtension.class)
class HotelReservationControllerTest {
    @Autowired
    private HotelReservationController hotelReservationController;

    @MockBean
    private HotelService hotelService;

    @MockBean
    private ReservationService reservationService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link HotelReservationController#congratulation()}
     */
    @Test
    void testCongratulation() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(hotelReservationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link HotelReservationController#deleteReservation(long)}
     */
    @Test
    void testDeleteReservation() throws Exception {
        doNothing().when(reservationService).deleteReservation((Long) any());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/reservation-delete");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("resId", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(hotelReservationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    /**
     * Method under test: {@link HotelReservationController#homePage(Model)}
     */
    @Test
    void testHomePage() throws Exception {
        when(hotelService.getAllCities()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(hotelReservationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cities", "currentReservation", "currentUser"))
                .andExpect(MockMvcResultMatchers.view().name("home-page"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("home-page"));
    }

    /**
     * Method under test: {@link HotelReservationController#homePage(Model)}
     */
    @Test
    void testHomePage2() throws Exception {
        when(hotelService.getAllCities()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(hotelReservationController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("cities", "currentReservation", "currentUser"))
                .andExpect(MockMvcResultMatchers.view().name("home-page"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("home-page"));
    }

    /**
     * Method under test: {@link HotelReservationController#newReservation(Model)}
     */
    @Test
    void testNewReservation() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(hotelReservationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link HotelReservationController#saveReservation()}
     */
    @Test
    void testSaveReservation() throws Exception {
        doNothing().when(reservationService).saveOrUpdateReservation();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveReservation");
        MockMvcBuilders.standaloneSetup(hotelReservationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/congratulation"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/congratulation"));
    }

    /**
     * Method under test: {@link HotelReservationController#updateReservation(int, Model)}
     */
    @Test
    void testUpdateReservation() throws Exception {
        when(reservationService.reservationToCurrentReservation((Long) any())).thenReturn(new CurrentReservation());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/reservation-edit");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("resId", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(hotelReservationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("newRes"))
                .andExpect(MockMvcResultMatchers.view().name("edit-reservation"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("edit-reservation"));
    }

    /**
     * Method under test: {@link HotelReservationController#yourReservations(Model)}
     */
    @Test
    void testYourReservations() throws Exception {
        when(reservationService.getAllReservationsForLoggedUser()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(hotelReservationController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

