package com.etstur.finalproject.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentUser;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#loginPage(Model)}
     */
    @Test
    void testLoginPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login-form-page");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("newUser"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("login"));
    }

    /**
     * Method under test: {@link UserController#loginPage(Model)}
     */
    @Test
    void testLoginPage2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/login-form-page", "Uri Variables");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("newUser"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("login"));
    }

    /**
     * Method under test: {@link UserController#logoutPage(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testLogoutPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logout");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login-form-page?logout"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login-form-page?logout"));
    }

    /**
     * Method under test: {@link UserController#logoutPage(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testLogoutPage2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/logout");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login-form-page?logout"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login-form-page?logout"));
    }

    /**
     * Method under test: {@link UserController#processRegister(CurrentUser, BindingResult, Model)}
     */
    @Test
    void testProcessRegister() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setUserName("janedoe");
        when(userService.findUserByEmail((String) any())).thenReturn(user);
        doNothing().when(userService).saveUser((CurrentUser) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/process_register");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(3))
                .andExpect(MockMvcResultMatchers.model().attributeExists("currentUser", "newUser", "registrationError"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("login"));
    }

    /**
     * Method under test: {@link UserController#register(Model)}
     */
    @Test
    void testRegister() throws Exception {
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

