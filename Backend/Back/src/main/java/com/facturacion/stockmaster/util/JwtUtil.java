package com.facturacion.stockmaster.util;

import com.facturacion.stockmaster.dto.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtUtil {

    @Value("${application.jwt.secret.key}")
    private String secret;

    @Value("${application.jwt.expiration.time}")
    private long jwtExpirationInMs;

    private Key key;

    @PostConstruct
    public void init() {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = Map.of(
                "roles", userDetails.getRoles(),
                "email", userDetails.getEmail(),
                "username", userDetails.getUsername(),
                "firstName", userDetails.getFirstName(),
                "lastName", userDetails.getLastName()
        );
        log.info("claims {}: ", claims);
        return createToken(claims, userDetails.getId());
    }

    private String createToken(Map<String, Object> claims, Long subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject.toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(key)
                .compact();
    }
}
