package com.msdev.sales.project.course.service.security.jwt;

import com.msdev.sales.project.course.SalesProjectCourseApplication;
import com.msdev.sales.project.course.domain.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signature-key}")
    private String signatureKey;

    public String generateToken(UserEntity user) {
        long exp = Long.valueOf(expiration);
        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(exp);
        Date date = Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .setSubject(user.getUserName())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime expirationDateTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(expirationDateTime);
        }catch (Exception e) {
            return false;
        }
    }

    public String getUserName(String token) throws ExpiredJwtException {
        return getClaims(token).getSubject();
    }

}
