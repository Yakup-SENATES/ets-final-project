package com.etstur.finalproject.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.etstur.finalproject.entity.Role;
import com.etstur.finalproject.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoleServiceImplTest {
    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    /**
     * Method under test: {@link RoleServiceImpl#findRoleByName(String)}
     */
    @Test
    void testFindRoleByName() {
        Role role = new Role();
        role.setName("Name");
        when(roleRepository.findByName((String) any())).thenReturn(role);
        assertSame(role, roleServiceImpl.findRoleByName("Name"));
        verify(roleRepository).findByName((String) any());
    }

    /**
     * Method under test: {@link RoleServiceImpl#save(Role)}
     */
    @Test
    void testSave() {
        Role role = new Role();
        role.setName("Name");
        when(roleRepository.save((Role) any())).thenReturn(role);

        Role role1 = new Role();
        role1.setName("Name");
        roleServiceImpl.save(role1);
        verify(roleRepository).save((Role) any());
        assertEquals("Name", role1.getName());
    }
}

