package com.gestion_etudiant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EtudiantDTO {

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @Pattern(regexp = "^[a-zA-Z\\s-]*$", message = "Le nom ne doit contenir que des lettres, espaces ou tirets")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50, message = "Le prénom ne doit pas dépasser 50 caractères")
    @Pattern(regexp = "^[a-zA-Z\\s-]*$", message = "Le prénom ne doit contenir que des lettres, espaces ou tirets")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "L'email doit avoir un format valide (ex: exemple@domaine.com)")
    private String email;
}




// salut, voici mon application que je developpe avec spring-boot dans vs code , xampp mysql et tester avec postman et documentation avec swagger .: package com.gestion_etudiant.config;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import javax.crypto.SecretKey;
// import java.io.IOException;
// import java.util.List;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final String SECRET_KEY = "votre-cle-secrete-tres-longue-et-securisee-1234567890"; // À remplacer par une clé sécurisée
//     private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String authHeader = request.getHeader("Authorization");
//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             String token = authHeader.substring(7);
//             try {
//                 Claims claims = Jwts.parser()
//                         .verifyWith(key)
//                         .build()
//                         .parseSignedClaims(token)
//                         .getPayload();
//                 String username = claims.getSubject();
//                 String role = claims.get("role", String.class);
//                 List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
//                 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                         username, null, authorities);
//                 SecurityContextHolder.getContext().setAuthentication(auth);
//             } catch (Exception e) {
//                 SecurityContextHolder.clearContext();
//             }
//         }
//         filterChain.doFilter(request, response);
//     }
// }......et package com.gestion_etudiant.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.http.HttpMethod;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final JwtAuthenticationFilter jwtAuthFilter;

//     public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
//         this.jwtAuthFilter = jwtAuthFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/api-docs/**", "/actuator/**").permitAll()
                
//                 .requestMatchers(HttpMethod.GET, "/api/v1/etudiants", "/api/v1/etudiants/**").hasAnyRole("ADMIN", "USER")
//                 .requestMatchers(HttpMethod.POST, "/api/v1/etudiants").hasRole("ADMIN")
//                 .requestMatchers(HttpMethod.PUT, "/api/v1/etudiants/**").hasRole("ADMIN")
//                 .requestMatchers(HttpMethod.DELETE, "/api/v1/etudiants/**").hasRole("ADMIN")
//                 .anyRequest().authenticated()
//             )
//             .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }

// .....et package com.gestion_etudiant.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import io.swagger.v3.oas.models.security.SecurityRequirement;
// import io.swagger.v3.oas.models.security.SecurityScheme;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springdoc.core.models.GroupedOpenApi;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class SwaggerConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 .info(new Info()
//                         .title("API de Gestion des Étudiants")
//                         .version("V.1.0.0")
//                         .description("Documentation interactive pour l'API REST de gestion des étudiants, développée par le group 1."))
//                 .addServersItem(new Server().url("http://localhost:8080").description("Serveur local"))
//                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                 .components(new io.swagger.v3.oas.models.Components()
//                         .addSecuritySchemes("bearerAuth",
//                                 new SecurityScheme()
//                                         .type(SecurityScheme.Type.HTTP)
//                                         .scheme("bearer")
//                                         .bearerFormat("JWT")));
//     }

//     @Bean
//     public GroupedOpenApi publicApi() {
//         return GroupedOpenApi.builder()
//                 .group("public")
//                 .pathsToMatch("/api/v1/**")
//                 .build();
//     }
// }.....et package com.gestion_etudiant.controller;

// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.security.Keys;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import javax.crypto.SecretKey;
// import java.util.Date;
// import java.util.Map;

// @RestController
// @RequestMapping("/api/v1/auth")
// public class AuthController {

//     private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

//     private final String SECRET_KEY = "votre-cle-secrete-tres-longue-et-securisee-1234567890"; // À remplacer par une clé sécurisée
//     private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

//     @Operation(summary = "Authentification", description = "Génère un token JWT pour un utilisateur.")
//     @ApiResponses(value = {
//         @ApiResponse(responseCode = "200", description = "Token généré avec succès"),
//         @ApiResponse(responseCode = "401", description = "Identifiants invalides")
//     })
//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestBody Map<String, String> credentials) {
//         String username = credentials.get("username");
//         String password = credentials.get("password");
//         logger.info("Tentative de connexion pour l'utilisateur : {}", username);
//         if ("admin".equals(username) && "admin123".equals(password)) {
//             String token = Jwts.builder()
//                     .subject(username)
//                     .claim("role", "ADMIN")
//                     .issuedAt(new Date())
//                     .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 jour
//                     .signWith(key)
//                     .compact();
//             logger.info("Token JWT généré pour l'utilisateur ADMIN : {}", username);
//             return ResponseEntity.ok(token);
//         } else if ("user".equals(username) && "user123".equals(password)) {
//             String token = Jwts.builder()
//                     .subject(username)
//                     .claim("role", "USER")
//                     .issuedAt(new Date())
//                     .expiration(new Date(System.currentTimeMillis() + 86400000)) // 1 jour
//                     .signWith(key)
//                     .compact();
//             logger.info("Token JWT généré pour l'utilisateur USER : {}", username);
//             return ResponseEntity.ok(token);
//         }
//         logger.warn("Échec de la connexion pour l'utilisateur : {}, identifiants invalides", username);
//         return ResponseEntity.status(401).body("Identifiants invalides");
//     }
// }........et package com.gestion_etudiant.controller;

// import com.gestion_etudiant.dto.EtudiantDTO;
// import com.gestion_etudiant.entity.Etudiant;
// import com.gestion_etudiant.service.EtudiantService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import jakarta.validation.Valid;
// import org.springframework.beans.BeanUtils;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.server.ResponseStatusException;

// import java.util.List;

// @RestController
// @RequestMapping("/api/v1/etudiants")
// public class EtudiantController {

//     private final EtudiantService service;

//     public EtudiantController(EtudiantService service) {
//         this.service = service;
//     }

//     @Operation(summary = "Ajouter un nouvel étudiant", description = "Crée un nouvel étudiant dans la base de données.")
//     @ApiResponses(value = {
//         @ApiResponse(responseCode = "201", description = "Étudiant créé avec succès"),
//         @ApiResponse(responseCode = "400", description = "Données d'entrée invalides")
//     })
//     @PostMapping
//     @ResponseStatus(HttpStatus.CREATED)
//     public Etudiant ajouterEtudiant(@Valid @RequestBody EtudiantDTO dto) {
//         Etudiant etudiant = new Etudiant();
//         BeanUtils.copyProperties(dto, etudiant);
//         return service.enregistrer(etudiant);
//     }

//     @Operation(summary = "Lister tous les étudiants", description = "Récupère la liste de tous les étudiants.")
//     @ApiResponse(responseCode = "200", description = "Liste des étudiants récupérée avec succès")
//     @GetMapping
//     public List<Etudiant> getTous() {
//         return service.lister();
//     }

//     @Operation(summary = "Obtenir un étudiant par ID", description = "Récupère les détails d'un étudiant spécifique par son ID.")
//     @ApiResponses(value = {
//         @ApiResponse(responseCode = "200", description = "Étudiant trouvé"),
//         @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
//     })
//     @GetMapping("/{id}")
//     public Etudiant getParId(@PathVariable Long id) {
//         Etudiant etudiant = service.obtenir(id);
//         if (etudiant == null) {
//             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé");
//         }
//         return etudiant;
//     }

//     @Operation(summary = "Modifier un étudiant", description = "Met à jour les informations d'un étudiant existant.")
//     @ApiResponses(value = {
//         @ApiResponse(responseCode = "200", description = "Étudiant modifié avec succès"),
//         @ApiResponse(responseCode = "404", description = "Étudiant non trouvé"),
//         @ApiResponse(responseCode = "400", description = "Données d'entrée invalides")
//     })
//     @PutMapping("/{id}")
//     public Etudiant modifierEtudiant(@PathVariable Long id, @Valid @RequestBody EtudiantDTO dto) {
//         Etudiant etudiant = service.obtenir(id);
//         if (etudiant == null) {
//             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé");
//         }
//         BeanUtils.copyProperties(dto, etudiant);
//         return service.modifier(id, etudiant);
//     }

//     @Operation(summary = "Supprimer un étudiant", description = "Supprime un étudiant par son ID.")
//     @ApiResponses(value = {
//         @ApiResponse(responseCode = "204", description = "Étudiant supprimé avec succès"),
//         @ApiResponse(responseCode = "404", description = "Étudiant non trouvé")
//     })
//     @DeleteMapping("/{id}")
//     @ResponseStatus(HttpStatus.NO_CONTENT)
//     public void supprimerEtudiant(@PathVariable Long id) {
//         Etudiant etudiant = service.obtenir(id);
//         if (etudiant == null) {
//             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Étudiant non trouvé");
//         }
//         service.supprimer(id);
//     }
// }.....et package com.gestion_etudiant.dto;

// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Size;
// import lombok.Data;

// @Data
// public class EtudiantDTO {

//     @NotBlank(message = "Le nom est obligatoire")
//     @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
//     @Pattern(regexp = "^[a-zA-Z\\s-]*$", message = "Le nom ne doit contenir que des lettres, espaces ou tirets")
//     private String nom;

//     @NotBlank(message = "Le prénom est obligatoire")
//     @Size(max = 50, message = "Le prénom ne doit pas dépasser 50 caractères")
//     @Pattern(regexp = "^[a-zA-Z\\s-]*$", message = "Le prénom ne doit contenir que des lettres, espaces ou tirets")
//     private String prenom;

//     @NotBlank(message = "L'email est obligatoire")
//     @Email(message = "L'email doit être valide")
//     @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "L'email doit avoir un format valide (ex: exemple@domaine.com)")
//     private String email;
// } .....et package com.gestion_etudiant.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Etudiant {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank(message = "Le nom est obligatoire")
//     private String nom;

//     @NotBlank(message = "Le prénom est obligatoire")
//     private String prenom;

//     @NotBlank(message = "L'email est obligatoire")
//     @Email(message = "L'email doit être valide")
//     @Column(unique = true) // Ajout de la contrainte d'unicité
//     private String email;
// }.....et package com.gestion_etudiant.exception;

// import lombok.Data;

// @Data
// public class ErrorResponse {
//     private int status;
//     private String message;
//     private long timestamp;

//     public ErrorResponse(int status, String message) {
//         this.status = status;
//         this.message = message;
//         this.timestamp = System.currentTimeMillis();
//     }
// }....et package com.gestion_etudiant.exception;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// // import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.server.ResponseStatusException;

// @ControllerAdvice
// public class GlobalExceptionHandler {

//     private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//     @ExceptionHandler(ResponseStatusException.class)
//     public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
//         logger.error("ResponseStatusException: {}", ex.getReason(), ex);
//         ErrorResponse error = new ErrorResponse(ex.getStatusCode().value(), ex.getReason());
//         return new ResponseEntity<>(error, ex.getStatusCode());
//     }

//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
//         String message = ex.getBindingResult().getFieldErrors().stream()
//                 .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                 .reduce((a, b) -> a + "; " + b)
//                 .orElse("Données d'entrée invalides");
//         logger.error("Validation error: {}", message, ex);
//         ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message);
//         return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//     }

//     @ExceptionHandler(DataIntegrityViolationException.class)
//     public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
//         logger.error("Data integrity violation: {}", ex.getMessage(), ex);
//         ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "L'email est déjà utilisé");
//         return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//     }

//     @ExceptionHandler(Exception.class)
//     public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//         logger.error("Unexpected error: {}", ex.getMessage(), ex);
//         ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erreur interne du serveur: " + ex.getMessage());
//         return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }.....et package com.gestion_etudiant.repository;

// import com.gestion_etudiant.entity.Etudiant;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
//     boolean existsByEmail(String email);
// }.....et package com.gestion_etudiant;

// import org.springframework.boot.SpringApplication;

// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class GestionEtudiantApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(GestionEtudiantApplication.class, args);
// 	}

// } .......et spring.application.name=gestion_etudiant
// # spring.datasource.url=jdbc:mysql://mysql_container:3306/api_etudiant_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
// spring.datasource.url=jdbc:mysql://localhost:3306/api_etudiant_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
// spring.datasource.username=root
// spring.datasource.password=

// spring.jpa.hibernate.ddl-auto=update
// spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
// spring.jpa.show-sql=true
// spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
// logging.level.com.gestion_etudiant=DEBUG
// logging.level.org.springframework.web=INFO
// logging.level.org.springdoc=DEBUG

// logging.file.name=logs/app.log
// logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n

// management.endpoints.web.exposure.include=health,metrics,prometheus
// management.endpoint.health.show-details=always
// management.metrics.tags.application=gestion_etudiant

// springdoc.api-docs.path=/api-docs
// springdoc.swagger-ui.path=/swagger-ui.html

// logging.level.org.springframework.beans.factory=DEBUG

// server.port=8080  .......et # Utiliser une image de base OpenJDK 17
// FROM openjdk:17-jdk-slim

// VOLUME /tmp

// # Définir le répertoire de travail dans le conteneur
// WORKDIR /app

// # Copier le fichier JAR généré par Maven
// COPY target/gestion_etudiant-0.0.1-SNAPSHOT.jar app.jar

// # Exposer le port 8080 (port par défaut de Spring Boot)
// EXPOSE 8080



// # Commande pour exécuter l'application
// ENTRYPOINT ["java", "-jar", "app.jar"]....et services:
//   mysql_container:
//     image: mysql:8.0
//     container_name: mysql_container
//     restart: always
//     environment:
//       MYSQL_ROOT_PASSWORD: root
//       MYSQL_DATABASE: api_etudiant_db
//     ports:
//       - "3307:3306"
//     volumes:
//       - mysql_data:/var/lib/mysql
//     healthcheck:
//       test: ["CMD", "mysqladmin", "ping", "-h", "127.0.0.1", "-uroot", "-proot"]
//       interval: 10s
//       timeout: 5s
//       retries: 10

//   gestion_etudiant_container:
//     build: .
//     container_name: gestion_etudiant_container
//     depends_on:
//       mysql_container:
//         condition: service_healthy
//     ports:
//       - "8080:8080"
//     environment:
//       SPRING_DATASOURCE_URL: jdbc:mysql://mysql_container:3306/api_etudiant_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
//       SPRING_DATASOURCE_USERNAME: root
//       SPRING_DATASOURCE_PASSWORD: root

// volumes:
//   mysql_data:
// ..........et <?xml version="1.0" encoding="UTF-8"?>
// <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
//     <modelVersion>4.0.0</modelVersion>
//     <parent>
//         <groupId>org.springframework.boot</groupId>
//         <artifactId>spring-boot-starter-parent</artifactId>
//         <version>3.3.5</version>
//         <relativePath/>
//     </parent>
//     <groupId>com</groupId>
//     <artifactId>gestion_etudiant</artifactId>
//     <version>0.0.1-SNAPSHOT</version>
//     <name>gestion_etudiant</name>
//     <description>Demo project for Spring Boot</description>
//     <properties>
//         <java.version>17</java.version>
//         <springdoc.version>2.6.0</springdoc.version>
//     </properties>
//     <dependencies>
//         <dependency>
//             <groupId>org.springframework.boot</groupId>
//             <artifactId>spring-boot-starter-data-jpa</artifactId>
//         </dependency>
//         <dependency>
//             <groupId>org.springframework.boot</groupId>
//             <artifactId>spring-boot-starter-web</artifactId>
//         </dependency>
//         <dependency>
//             <groupId>org.springframework.boot</groupId>
//             <artifactId>spring-boot-starter-validation</artifactId>
//         </dependency>
//         <dependency>
//             <groupId>org.springframework.boot</groupId>
//             <artifactId>spring-boot-starter-security</artifactId>
//         </dependency>
//         <dependency>
//             <groupId>org.springframework.boot</groupId>
//             <artifactId>spring-boot-starter-actuator</artifactId>
//         </dependency>
//         <dependency>
//             <groupId>io.micrometer</groupId>
//             <artifactId>micrometer-registry-prometheus</artifactId>
//         </dependency>
//         <dependency>
//             <groupId>io.jsonwebtoken</groupId>
//             <artifactId>jjwt-api</artifactId>
//             <version>0.12.6</version>
//         </dependency>
//         <dependency>
//             <groupId>io.jsonwebtoken</groupId>
//             <artifactId>jjwt-impl</artifactId>
//             <version>0.12.6</version>
//             <scope>runtime</scope>
//         </dependency>
//         <dependency>
//             <groupId>io.jsonwebtoken</groupId>
//             <artifactId>jjwt-jackson</artifactId>
//             <version>0.12.6</version>
//             <scope>runtime</scope>
//         </dependency>
//         <dependency>
//             <groupId>com.mysql</groupId>
//             <artifactId>mysql-connector-j</artifactId>
//             <scope>runtime</scope>
//         </dependency>
//         <dependency>
//             <groupId>org.projectlombok</groupId>
//             <artifactId>lombok</artifactId>
//             <optional>true</optional>
//         </dependency>
//         <dependency>
//             <groupId>org.springframework.boot</groupId>
//             <artifactId>spring-boot-starter-test</artifactId>
//             <scope>test</scope>
//         </dependency>
//         <dependency>
//             <groupId>org.springdoc</groupId>
//             <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
//             <version>${springdoc.version}</version>
//         </dependency>
//         <dependency>
//             <groupId>org.owasp.encoder</groupId>
//             <artifactId>encoder</artifactId>
//             <version>1.2.3</version>
//         </dependency>
//         <dependency>
//             <groupId>org.springframework.boot</groupId>
//             <artifactId>spring-boot-starter-cache</artifactId>
//         </dependency>
//         <dependency>
//             <groupId>com.bucket4j</groupId>
//             <artifactId>bucket4j_jdk17-core</artifactId>
//             <version>8.14.0</version>
//         </dependency>
//     </dependencies>
//     <repositories>
//         <repository>
//             <id>central</id>
//             <url>https://repo.maven.apache.org/maven2</url>
//         </repository>
//     </repositories>
//     <build>
//         <plugins>
//             <plugin>
//                 <groupId>org.apache.maven.plugins</groupId>
//                 <artifactId>maven-compiler-plugin</artifactId>
//                 <configuration>
//                     <annotationProcessorPaths>
//                         <path>
//                             <groupId>org.projectlombok</groupId>
//                             <artifactId>lombok</artifactId>
//                         </path>
//                     </annotationProcessorPaths>
//                 </configuration>
//             </plugin>
//             <plugin>
//                 <groupId>org.springframework.boot</groupId>
//                 <artifactId>spring-boot-maven-plugin</artifactId>
//                 <configuration>
//                     <excludes>
//                         <exclude>
//                             <groupId>org.projectlombok</groupId>
//                             <artifactId>lombok</artifactId>
//                         </exclude>
//                     </excludes>
//                 </configuration>
//             </plugin>
//             <plugin>
//                 <groupId>org.jacoco</groupId>
//                 <artifactId>jacoco-maven-plugin</artifactId>
//                 <version>0.8.12</version>
//                 <executions>
//                     <execution>
//                         <goals>
//                             <goal>prepare-agent</goal>
//                         </goals>
//                     </execution>
//                     <execution>
//                         <id>report</id>
//                         <phase>test</phase>
//                         <goals>
//                             <goal>report</goal>
//                         </goals>
//                     </execution>
//                 </executions>
//             </plugin>
//         </plugins>
//     </build>
// </project>....comme tu peux les constater ..j'ai deja fini d'integrer tout ces elements sauf le 8 eme  point pour l'element : - Implement rate limiting/throttlinget ce ceqe biensur tu dois m'aider a integrer dan mon API gestion_etudiant   : PROJETS REST API
// --------------------------
// Améilorez vos projets pour y intégrer les éléments suivants:


// 1. API Design
// - Use RESTful naming conventions
// - Plan versioning (e.g., /api/v1/)

// 2. Validation & Error Handling
// - Validate inputs (e.g., using DTOs)
// - Return meaningful HTTP status codes
// - Include consistent error response format

// 3. Authentication & Authorization
// - Use JWT, OAuth2, or API keys
// - Define user roles and permissions

// 4. Logging & Monitoring
// - Log requests/responses/errors (e.g., with Logback, ELK)
// - Use tools like Prometheus, Grafana, or New Relic

// 5. Testing
// - Unit tests (e.g., JUnit)
// - Integration tests (e.g., TestRestTemplate)
// - API testing (e.g., Postman, Newman)

// 6.Documentation
// . Swagger/OpenAPI

// 7. Deployment
// - Containerize with Docker
// - CI/CD with Jenkins
// - Host on services like Render, etc.

// 8. Security Best Practices
// - Sanitize inputs to avoid SQL/XSS attacks
// - Implement rate limiting/throttling   