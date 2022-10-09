package com.etstur.finalproject.service.impl;

import com.etstur.finalproject.entity.Role;
import com.etstur.finalproject.repository.RoleRepository;
import com.etstur.finalproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }


}
