package com.etstur.finalproject.service;


import com.etstur.finalproject.entity.Role;

public interface RoleService {

    Role findRoleByName(String name);

    void save(Role role);
}
