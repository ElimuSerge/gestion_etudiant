// package com.gestion_etudiant.config;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.List;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private final String SECRET_KEY = "votre-clé-secrète-ici"; // À remplacer par une clé sécurisée

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String authHeader = request.getHeader("Authorization");
//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             String token = authHeader.substring(7);
//             try {
//                 Claims claims = Jwts.parser()
//                         .setSigningKey(SECRET_KEY.getBytes())
//                         .build()
//                         .parseClaimsJws(token)
//                         .getBody();
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
// }