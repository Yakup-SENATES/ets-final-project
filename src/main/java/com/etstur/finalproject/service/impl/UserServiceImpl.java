package com.etstur.finalproject.service.impl;

import com.etstur.finalproject.entity.Role;
import com.etstur.finalproject.entity.User;
import com.etstur.finalproject.repository.UserRepository;
import com.etstur.finalproject.service.UserService;
import com.etstur.finalproject.temp.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    //service pattern to manage transactions
    //and handle services for user between server and client

    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;
    private BCryptPasswordEncoder passwordEncoder;



    @PostConstruct
    public void init() {
        passwordEncoder = new BCryptPasswordEncoder();
        Role role = new Role();
        role.setName("ROLE_USER");
        roleService.save(role);
    }


    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    @Transactional
    public void saveUser(CurrentUser currentUser) {
        User newUser = new User();

        newUser.setUserName(currentUser.getUserName());
        newUser.setEmail(currentUser.getEmail());
        //bcrypt password to save it hashing in database
        newUser.setPassword(passwordEncoder.encode(currentUser.getPassword()));
        newUser.setRoles(List.of(roleService.findRoleByName("ROLE_USER")));
        userRepository.save(newUser);
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }


    @Override
    @Transactional
    public long getLoggedUserId() {
        return userRepository.findByUserName(loggedUserEmail()).getId();
    }

    private String loggedUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
}
