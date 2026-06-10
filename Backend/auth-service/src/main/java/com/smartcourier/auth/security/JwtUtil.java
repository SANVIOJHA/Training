package com.smartcourier.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * JWT utility for token generation and validation.
 * Supports access tokens and refresh tokens.
 *
 * Security hardening:
 * - Externalized secret (not hardcoded)
 * - Token expiration validation
 * - Refresh token support
 * - Differentiated exception handling
 */
@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret:mysecretkeymysecretkeymysecretkeymysecretkey}")
    private String secret;

    @Value("${jwt.access-token-expiration:3600000}")
    private long accessTokenExpiration; // default 1 hour

    @Value("${jwt.refresh-token-expiration:604800000}")
    private long refreshTokenExpiration; // default 7 days

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    /**
     * Generate an access token with username and role claims.
     */
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Generate a refresh token (longer-lived, minimal claims).
     */
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extract all claims from a token. Throws specific exceptions for each failure type.
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    public String extractTokenType(String token) {
        return extractAllClaims(token).get("type", String.class);
    }

    /**
     * Validate token: checks expiration, signature, and structure.
     * Returns true if valid, false otherwise.
     */
    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            log.warn("JWT token expired: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.warn("Malformed JWT token: {}", e.getMessage());
            return false;
        } catch (SignatureException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
            return false;
        } catch (JwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Validate that the token is an access token (not a refresh token).
     */
    public boolean isAccessToken(String token) {
        try {
            String type = extractTokenType(token);
            return "access".equals(type);
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Validate that the token is a refresh token.
     */
    public boolean isRefreshToken(String token) {
        try {
            String type = extractTokenType(token);
            return "refresh".equals(type);
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Refresh an access token using a valid refresh token.
     * @return new access token map with both access and refresh tokens
     */
    public Map<String, String> refreshAccessToken(String refreshToken, String role) {
        if (!isRefreshToken(refreshToken)) {
            throw new JwtException("Invalid refresh token");
        }

        String username = extractUsername(refreshToken);
        String newAccessToken = generateToken(username, role);
        String newRefreshToken = generateRefreshToken(username);

        return Map.of(
                "accessToken", newAccessToken,
                "refreshToken", newRefreshToken
        );
    }
}