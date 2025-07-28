package org.practica.dominio;


import org.practica.models.ERole;
import org.practica.models.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository {
    RoleEntity findById(Long id);
    void save(RoleEntity role);
    Optional<RoleEntity> findByName(ERole role);
}
