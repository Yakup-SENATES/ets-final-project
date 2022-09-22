package com.etstur.finalproject.dao;


import com.etstur.finalproject.entity.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

// Dao Pattern for User
@Component
public class UserDao implements Dao<User> {

    private List<User> userList = new ArrayList<>();

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(userList.get((int) id));
    }

    @Override
    public List<User> getAll() {
        /*
        return Optional.ofNullable(userList).orElse(new ArrayList<>());
         */
        return userList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public void save(User user) {
        userList.add(user);
    }

    @Override
    public void update(User user) {
        userList.set(user.getId().intValue(), user);
    }

    @Override
    public void delete(User user) {
        userList.remove(user);
        //userList.set(user.getId().intValue(), null);
    }
}
