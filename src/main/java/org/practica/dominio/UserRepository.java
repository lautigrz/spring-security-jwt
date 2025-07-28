package org.practica.dominio;

import org.practica.models.UserEntity;

import java.util.Optional;

public interface UserRepository{

    void save(UserEntity userEntity);
    Optional<UserEntity> findByUsername(String username);
    void deleteById(Long id);

}
