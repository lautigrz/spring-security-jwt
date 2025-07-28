package org.practica.dominio;

import org.practica.models.ERole;
import org.practica.models.RoleEntity;

import java.util.Optional;

public interface ServiceRole {
    RoleEntity findById(Long id);
    RoleEntity save(RoleEntity role);

    Optional<RoleEntity> findByRole(String role);
}
