package org.practica.controller;

import javax.validation.Valid;
import org.practica.controller.dto.CreateUserDTO;
import org.practica.dominio.ServiceRole;
import org.practica.dominio.ServiceUser;
import org.practica.models.ERole;
import org.practica.models.RoleEntity;
import org.practica.models.UserEntity;
import org.practica.dominio.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceRole serviceRole;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/notSecured")
    public String getNotSecured(){

        return passwordEncoder.encode("password");
    }

    @GetMapping("/secured")
    public String getSecured(){
        return "Secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        Set<RoleEntity> roles = createUserDTO.getRoles().stream().map(roleName -> serviceRole.findByRole(roleName)
                        .orElseGet(() -> {
                            RoleEntity newRole = RoleEntity.builder()
                                    .name(ERole.valueOf(roleName))
                                    .build();
                            return serviceRole.save(newRole);
                        }))
                .collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .email(createUserDTO.getEmail())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .roles(roles)
                .build();

        serviceUser.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        serviceUser.deleteById(Long.parseLong(id));
        return "Usuario eliminado con id ".concat(id);
    }




}
