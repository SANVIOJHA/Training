package com.smartcourier.gateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class JwtUtilTest {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey";

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", SECRET);
    }

    @Test
    void validateTokenAcceptsAccessTokenSignedWithConfiguredSecret() {
        String token = token("alice", "CUSTOMER", "access");

        assertNull(jwtUtil.validateToken(token));
        assertEquals("alice", jwtUtil.extractUsername(token));
        assertEquals("CUSTOMER", jwtUtil.extractRole(token));
    }

    @Test
    void validateTokenRejectsRefreshTokenForApiAccess() {
        String token = token("alice", null, "refresh");

        assertEquals("Refresh tokens cannot be used for API access", jwtUtil.validateToken(token));
    }

    @Test
    void validateTokenReturnsSignatureErrorForDifferentSecret() {
        String token = Jwts.builder()
                .setSubject("alice")
                .claim("role", "CUSTOMER")
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(Keys.hmacShaKeyFor("differentsecretkeydifferentsecretkey1234".getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();

        assertEquals("Invalid token signature", jwtUtil.validateToken(token));
    }

    private String token(String username, String role, String type) {
        var builder = Jwts.builder()
                .setSubject(username)
                .claim("type", type)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60000));
        if (role != null) {
            builder.claim("role", role);
        }
        return builder
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }
}
