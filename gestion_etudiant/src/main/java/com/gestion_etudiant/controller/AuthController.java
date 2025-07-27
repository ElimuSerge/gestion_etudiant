// package com.gestion_etudiant.controller;

// import io.jsonwebtoken.Jwts;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Date;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/v1/auth")
// public class AuthController {

//     private final String SECRET_KEY = "votre-clé-secrète-ici"; // À remplacer par une clé sécurisée

//     @Operation(summary = "Authentification", description = "Génère un token JWT pour un utilisateur.")
//     @ApiResponse(responseCode = "200", description = "Token généré avec succès")
//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
//         String username = credentials.get("username");
//         String password = credentials.get("password");
//         // Simuler une vérification (remplacer par une vraie logique avec une base de données)
//         if ("admin".equals(username) && "password".equals(password)) {
//             String token = Jwts.builder()
//                     .setSubject(username)
//                     .claim("role", "ADMIN")
//                     .setIssuedAt(new Date())
//                     .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 jour
//                     .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
//                     .compact();
//             return ResponseEntity.ok(token);
//         }
//         return ResponseEntity.status(401).body("Identifiants invalides");
//     }
// }