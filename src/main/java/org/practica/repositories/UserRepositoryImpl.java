package org.practica.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.practica.dominio.UserRepository;
import org.practica.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(UserEntity userEntity) {
        sessionFactory.getCurrentSession().save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        String hql = "FROM UserEntity WHERE username = :username";
        UserEntity user = sessionFactory.getCurrentSession()
                .createQuery(hql, UserEntity.class)
                .setParameter("username", username)
                .uniqueResult();
        return Optional.ofNullable(user);
    }


    @Override
    public void deleteById(Long id) {
        UserEntity user = sessionFactory.getCurrentSession().get(UserEntity.class, id);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
