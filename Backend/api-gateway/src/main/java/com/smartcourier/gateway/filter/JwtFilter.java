package com.smartcourier.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcourier.gateway.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

/**
 * Gateway JWT filter with enterprise-grade security:
 * 1. Validates JWT token with differentiated error handling
 * 2. Extracts username & role
 * 3. Applies role-based authorization
 * 4. Forwards user identity to downstream services via headers
 * 5. Returns proper JSON error responses (not empty bodies)
 */
@Component
public class JwtFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod().name();

        log.info("Gateway → {} {}", method, path);

        // ================= PUBLIC ENDPOINTS =================
        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }

        // ================= TOKEN EXTRACTION =================
        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Gateway: Missing or invalid Authorization header for {}", path);
            return errorResponse(exchange, HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7).trim();

        // ================= TOKEN VALIDATION =================
        String validationError = jwtUtil.validateToken(token);
        if (validationError != null) {
            log.warn("Gateway: Token validation failed for {}: {}", path, validationError);
            return errorResponse(exchange, HttpStatus.UNAUTHORIZED, validationError);
        }

        // ================= EXTRACT IDENTITY =================
        String username = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);

        // ================= ROLE-BASED AUTHORIZATION =================
        if (path.contains("/admin") && !"ADMIN".equalsIgnoreCase(role)) {
            log.warn("Gateway: Access denied for user '{}' with role '{}' on admin path", username, role);
            return errorResponse(exchange, HttpStatus.FORBIDDEN, "Access denied: ADMIN role required");
        }

        if (path.contains("/deliveries")
                && !("ADMIN".equalsIgnoreCase(role) || "CUSTOMER".equalsIgnoreCase(role))) {
            log.warn("Gateway: Access denied for user '{}' with role '{}' on deliveries path", username, role);
            return errorResponse(exchange, HttpStatus.FORBIDDEN, "Access denied: insufficient permissions");
        }

        // ================= HEADER INJECTION =================
        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .headers(headers -> {
                            headers.set("Authorization", "Bearer " + token);
                            headers.set("X-User", username);
                            headers.set("X-Role", role);
                        })
                        .build())
                .build();

        log.debug("Gateway: Authorized user='{}' role='{}' → {}", username, role, path);
        return chain.filter(modifiedExchange);
    }

    /**
     * Check if the path is publicly accessible (no auth required).
     */
    private boolean isPublicPath(String path) {
        return path.contains("/auth/login")
                || path.contains("/auth/signup")
                || path.contains("/auth/refresh")
                || path.contains("/actuator")
                || path.contains("/swagger-ui")
                || path.contains("/v3/api-docs");
    }

    /**
     * Build a proper JSON error response with the standard API response format.
     */
    private Mono<Void> errorResponse(ServerWebExchange exchange, HttpStatus status, String message) {
        exchange.getResponse().setStatusCode(status);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "success", false,
                "message", message,
                "timestamp", Instant.now().toString()
        );

        try {
            byte[] bytes = objectMapper.writeValueAsBytes(body);
            DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
            return exchange.getResponse().writeWith(Mono.just(buffer));
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize error response", e);
            return exchange.getResponse().setComplete();
        }
    }
}
