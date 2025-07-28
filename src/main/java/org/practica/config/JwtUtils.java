package org.practica.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private String secretKey = "yTmN8glsue5zSAqO0x2E102cZXEpLoaJjgk5bsmI7Y9s9toR1YNK2UF8N53z9DPr";
    private String expiration = "20000000";


    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(getSignatureKey(),SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        }catch(Exception e) {
            logger.error("error al validar el token " + e.getMessage());
            return false;
        }

    }

    // obtener username del token

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // obtener un solo claim

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // obtener claims del token

    public Claims extractAllClaims(String token){
       return Jwts.parserBuilder().setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Key getSignatureKey(){
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
