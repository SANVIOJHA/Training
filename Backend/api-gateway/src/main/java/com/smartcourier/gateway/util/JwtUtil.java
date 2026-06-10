package com.smartcourier.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Gateway-level JWT utility.
 * Only validates tokens — never generates them (that's auth-service's job).
 *
 * Differentiates between:
 * - Expired tokens → 401 with "Token expired" message
 * - Malformed tokens → 401 with "Malformed token" message
 * - Invalid signatures → 401 with "Invalid signature" message
 */
@Component
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret:mysecretkeymysecretkeymysecretkeymysecretkey}")
    private String secret;

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    /**
     * Extract all claims — throws specific exceptions.
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

    /**
     * Validate the token and return the failure reason if invalid.
     *
     * @return null if valid, error message string if invalid
     */
    public String validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            if (claims.getExpiration().before(new Date())) {
                return "Token has expired";
            }
            // Ensure it's an access token, not a refresh token
            String type = claims.get("type", String.class);
            if ("refresh".equals(type)) {
                return "Refresh tokens cannot be used for API access";
            }
            return null; // valid
        } catch (ExpiredJwtException e) {
            log.warn("Gateway: Expired JWT detected");
            return "Token has expired";
        } catch (MalformedJwtException e) {
            log.warn("Gateway: Malformed JWT detected");
            return "Malformed token";
        } catch (SignatureException e) {
            log.warn("Gateway: Invalid JWT signature detected");
            return "Invalid token signature";
        } catch (JwtException e) {
            log.warn("Gateway: Invalid JWT: {}", e.getMessage());
            return "Invalid token";
        } catch (Exception e) {
            log.error("Gateway: Unexpected JWT error: {}", e.getMessage());
            return "Token validation failed";
        }
    }
}