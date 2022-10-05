package ua.ponarin.trivia.authenticator.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.ponarin.trivia.authenticator.model.User;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtUtils implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;
    @Value("${jwt.secret}")
    private String secret;
    @Value("#{${jwt.expirationTime} * 60 * 1000}")
    private Long expirationTime;

    public String generateToken(User user) {
        return Jwts.builder()
                .claim("userId", user.getId())
                .claim("userRole", user.getRole())
                .setIssuer("trivia-authenticator")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parse(token);
    }
}
