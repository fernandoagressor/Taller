package com.camiones.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;


    public String generarToken(UserDetails userDetails) {

        Date ahora = new Date();

        Date expiracion = new Date(
                ahora.getTime() + jwtExpiration
        );

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(getSigningKey())
                .compact();
    }


    public String extraerUsername(String token) {

        return extraerClaim(
                token,
                Claims::getSubject
        );
    }


    public boolean validarToken(
            String token,
            UserDetails userDetails) {

        String username = extraerUsername(token);

        return username.equals(userDetails.getUsername())
                && !tokenExpirado(token);
    }


    private boolean tokenExpirado(String token) {

        Date expiracion = extraerClaim(
                token,
                Claims::getExpiration
        );

        return expiracion.before(new Date());
    }


    public <T> T extraerClaim(
            String token,
            Function<Claims, T> claimsResolver) {

        Claims claims = extraerTodosLosClaims(token);

        return claimsResolver.apply(claims);
    }


    private Claims extraerTodosLosClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    private SecretKey getSigningKey() {

        byte[] keyBytes =
                Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}