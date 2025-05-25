package app.visualmusic.security.spring.security.adapter;

import app.visualmusic.auth.domain.RefreshToken;
import app.visualmusic.auth.domain.User;
import app.visualmusic.auth.port.output.security.JwtTokenProvider;
import app.visualmusic.security.spring.security.property.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JjwtAdapter implements JwtTokenProvider {
    private final JwtProperties jwtProperties;

    private SecretKey accessSecret;
    private SecretKey refreshSecret;

    @PostConstruct
    private void init() {
        this.accessSecret = toSecretKey(jwtProperties.getAccessSecret());
        this.refreshSecret = toSecretKey(jwtProperties.getRefreshSecret());
    }

    private SecretKey toSecretKey(String secret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    @Override
    public String generateAccessToken(User user) {
        return Jwts
                .builder()
                .signWith(accessSecret)
                .subject(user.getId().toString())
                .claim("role", user.getRole().getName())
                .issuedAt(Date.from(Instant.now()))
                .expiration(parseExpiration(jwtProperties.getAccessTokenExpiration()))
                .compact();
    }

    @Override
    public RefreshToken generateRefreshToken(User user, String deviceId) {
        Instant iat = Instant.now();

        String jwtsToken = Jwts
                .builder()
                .signWith(refreshSecret)
                .subject(user.getId().toString())
                .issuedAt(Date.from(iat))
                .expiration(parseExpiration(jwtProperties.getRefreshTokenExpiration()))
                .compact();

        return RefreshToken.builder()
                .user(user)
                .token(jwtsToken)
                .deviceId(deviceId)
                .expiresAt(iat)
                .build();
    }

    private Date parseExpiration(String expiration) {
        return Date.from(Instant.now().plus(Duration.parse(expiration)));
    }

    @Override
    public boolean isAccessTokenValid(String token) {
        try {
            parseToken(token, accessSecret);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public boolean isRefreshTokenValid(String token) {
        try {
            parseToken(token, refreshSecret);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private void parseToken(String token, SecretKey secret) {
        Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token);
    }

    @Override
    public Long getUserId(String token, boolean isAccessToken) {
        SecretKey secret = isAccessToken
                ? accessSecret
                : refreshSecret;

        String sub = extractClaims(token, secret, Claims::getSubject);
        return Long.valueOf(sub);
    }

    @Override
    public String getRole(String accessToken) {
        return extractClaims(
                accessToken,
                accessSecret,
                claims -> claims.get("role", String.class)
        );
    }

    private <T> T extractClaims(String token, SecretKey secret, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token, secret);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, SecretKey secret) {
        return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
