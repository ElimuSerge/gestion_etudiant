package com.gestion_etudiant.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final String SECRET_KEY = "votre-cle-secrete-tres-longue-et-securisee-1234567890"; // À remplacer par une clé sécurisée
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    @Operation(summary = "Authentification", description = "Génère un token JWT pour un utilisateur.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Token généré avec succès"),
        @ApiResponse(responseCode = "401", description = "Identifiants invalides")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        logger.info("Tentative de connexion pour l'utilisateur : {}", username);
        if ("admin".equals(username) && "admin123".equals(password)) {
            String token = Jwts.builder()
                    .subject(username)
                    .claim("role", "ADMIN")
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 jour
                    .signWith(key)
                    .compact();
            logger.info("Token JWT généré pour l'utilisateur ADMIN : {}", username);
            return ResponseEntity.ok(token);
        } else if ("user".equals(username) && "user123".equals(password)) {
            String token = Jwts.builder()
                    .subject(username)
                    .claim("role", "USER")
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 jour
                    .signWith(key)
                    .compact();
            logger.info("Token JWT généré pour l'utilisateur USER : {}", username);
            return ResponseEntity.ok(token);
        }
        logger.warn("Échec de la connexion pour l'utilisateur : {}, identifiants invalides", username);
        return ResponseEntity.status(401).body("Identifiants invalides");
    }
}