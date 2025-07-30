package org.practica.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {
    @GetMapping("/accesAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accesAdmin() {
        return "Accediste como rol de admin!";
    }
    @GetMapping("/accesUser")
    @PreAuthorize("hasRole('USER')")
    public String accesUser() {
        return "Accediste como rol de usuario!";
    }
    @GetMapping("/accesInvited")
    @PreAuthorize("hasRole('INVITED')")
    public String accesInvited() {
        return "Accesite como rol de invitado!";
    }
}
