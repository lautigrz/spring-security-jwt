package org.practica.dominio;

import org.practica.models.ERole;
import org.practica.models.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ServiceRoleImpl implements ServiceRole {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleEntity findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public RoleEntity save(RoleEntity role) {
        roleRepository.save(role);
        return role;
    }

    @Override
    public Optional<RoleEntity> findByRole(String roleName) {
        ERole role = ERole.valueOf(roleName);
        return roleRepository.findByName(role);
    }
}
