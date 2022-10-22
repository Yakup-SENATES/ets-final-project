package com.etstur.finalproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.etstur.finalproject.entity.Reservation;
import com.etstur.finalproject.entity.Role;
import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.repository.UserRepository;
import com.etstur.finalproject.temp.CurrentUser;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private RoleServiceImpl roleServiceImpl;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#findUserByEmail(String)}
     */
    @Test
    void testFindUserByEmail() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setUserName("janedoe");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        assertSame(user, userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#findUserByEmail(String)}
     */
    @Test
    void testFindUserByEmail_whenUserNotFound_thanThrowUserNameNotFoundException() {
        when(userRepository.findByEmail((String) any())).thenThrow(new UsernameNotFoundException("Msg"));
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.findUserByEmail("jane.doe@example.org"));
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#saveUser(CurrentUser)}
     */
    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setUserName("janedoe");
        when(userRepository.save((User) any())).thenReturn(user);

        Role role = new Role();
        role.setName("Name");
        when(roleServiceImpl.findRoleByName((String) any())).thenReturn(role);
        userServiceImpl.saveUser(new CurrentUser("janedoe", "iloveyou", "iloveyou", "jane.doe@example.org"));
        verify(userRepository).save((User) any());
        verify(roleServiceImpl).findRoleByName((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#saveUser(CurrentUser)}
     */
    @Test
    void testSaveUser_whenUserRoleNotFound_thanThrowException() {
        when(userRepository.save((User) any())).thenThrow(new UsernameNotFoundException("ROLE_USER"));

        Role role = new Role();
        role.setName("Name");
        when(roleServiceImpl.findRoleByName((String) any())).thenReturn(role);
        assertThrows(UsernameNotFoundException.class,
                () -> userServiceImpl.saveUser(new CurrentUser("janedoe", "iloveyou", "iloveyou", "jane.doe@example.org")));
        verify(userRepository).save((User) any());
        verify(roleServiceImpl).findRoleByName((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setUserName("janedoe");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        UserDetails actualLoadUserByUsernameResult = userServiceImpl.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRoles()).thenReturn(new ArrayList<>());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setId((Long) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setReservations(any());
        doNothing().when(user).setRoles(any());
        doNothing().when(user).setUserName((String) any());
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setReservations(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        user.setUserName("janedoe");
        when(userRepository.findByEmail((String) any())).thenReturn(user);
        UserDetails actualLoadUserByUsernameResult = userServiceImpl.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(userRepository).findByEmail((String) any());
        verify(user).getEmail();
        verify(user).getPassword();
        verify(user).getRoles();
        verify(user).setEmail((String) any());
        verify(user).setId((Long) any());
        verify(user).setPassword((String) any());
        verify(user).setReservations(any());
        verify(user).setRoles(any());
        verify(user).setUserName((String) any());
    }


    /**
     * Method under test: {@link UserServiceImpl#init()}
     */
    @Test
    void testInit() {
        doNothing().when(roleServiceImpl).save((Role) any());
        userServiceImpl.init();
        verify(roleServiceImpl).save((Role) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#init()}
     */
    @Test
    void testInit2() {
        doThrow(new UsernameNotFoundException("ROLE_USER")).when(roleServiceImpl).save((Role) any());
        assertThrows(UsernameNotFoundException.class, () -> userServiceImpl.init());
        verify(roleServiceImpl).save((Role) any());
    }
}

