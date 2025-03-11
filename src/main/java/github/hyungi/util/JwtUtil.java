package github.hyungi.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    private final long expiration;

    public JwtUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") long expiration) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    public String generateToken(UUID userId) {
        Date date = new Date();
        return Jwts.builder()
                .signWith(secretKey, Jwts.SIG.HS256)
                .subject(userId.toString())
                .issuedAt(date)
                .expiration(getExpiredDate(date))
                .compact();
    }

    private Date getExpiredDate(Date date) {
        return new Date(date.getTime() + expiration);
    }

    public UUID parseToken(String token) {
        return UUID.fromString(
                Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject()
        );
    }
}
