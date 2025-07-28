package org.practica.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.practica.dominio.RoleRepository;
import org.practica.models.ERole;
import org.practica.models.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RoleEntity findById(Long id) {
        return sessionFactory.getCurrentSession().get(RoleEntity.class, id);
    }

    @Override
    public void save(RoleEntity role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public Optional<RoleEntity> findByName(ERole roleName) {
        RoleEntity role = (RoleEntity) sessionFactory.getCurrentSession()
                .createQuery("FROM RoleEntity WHERE name = :name", RoleEntity.class)
                .setParameter("name", roleName)
                .uniqueResult();
        return Optional.ofNullable(role);
    }
}
