package com.example.minisaraha.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt-secret}")
    private String secretKey;

    public String generateJwtToken(String username) {
        Date currDate = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currDate);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date expiryDate = calendar.getTime();

        Claims claims = Jwts.claims().setSubject(username);


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(currDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
    public Boolean validateJwtToken(String token, DBUserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }
    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}