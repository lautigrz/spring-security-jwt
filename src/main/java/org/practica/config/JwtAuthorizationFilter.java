package org.practica.config;

import org.practica.dominio.ServiceUserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ServiceUserImpl serviceUser;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            if (jwtUtils.isTokenValid(jwt)) {
                String username = jwtUtils.getUsernameFromToken(jwt);
                var userDetails = serviceUser.loadUserByUsername(username);
                if (userDetails != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(
                            username, null, userDetails.getAuthorities());
                    authentication.setDetails(userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }
            }


        }
        filterChain.doFilter(request, response);
    }
}
