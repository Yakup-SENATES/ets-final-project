package com.etstur.finalproject.dao.impl;

import com.etstur.finalproject.dao.RoleDao;
import com.etstur.finalproject.entity.Role;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class RoleImpl implements RoleDao {

    private final EntityManager entityManager;
    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public Role findRoleByName(String name) {
        return getSession().createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
