package com.amadeus.flightsearchapi.config;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
    @Value("${application.jwt_secret}")
    private String jwtSecret;

    @Value("${application.jwt_expires_in}")
    private long jwtExpireDuration;

    public String generateToken(UserPrincipal user){
        Date expireDate = new Date(new Date().getTime() + jwtExpireDuration);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("auth", user.getAuthorities())
                .claim("uid", user.getId())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            return !Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration().before(new Date());
        }catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
