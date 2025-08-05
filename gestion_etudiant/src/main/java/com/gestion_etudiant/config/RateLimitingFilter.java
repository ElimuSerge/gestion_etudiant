// package com.gestion_etudiant.config;

// import io.github.bucket4j.Bucket;
// import io.github.bucket4j.ConsumptionProbe;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import org.springframework.core.Ordered;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;

// @Component
// public class RateLimitingFilter extends OncePerRequestFilter implements Ordered {

//     private final Bucket bucket;

//     public RateLimitingFilter(RateLimiterConfig rateLimiterConfig) {
//         this.bucket = rateLimiterConfig.createBucket();
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
//         if (probe.isConsumed()) {
//             response.addHeader("X-Rate-Limit-Remaining", Long.toString(probe.getRemainingTokens()));
//             filterChain.doFilter(request, response);
//         } else {
//             long waitForRefillNanos = probe.getNanosToWaitForRefill();
//             response.addHeader("X-Rate-Limit-Retry-After-Milliseconds", Long.toString(waitForRefillNanos / 1_000_000));
//             response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "Trop de requêtes, veuillez réessayer plus tard.");
//         }
//     }

//     @Override
//     public int getOrder() {
//         return Ordered.HIGHEST_PRECEDENCE + 1; // Juste après JwtAuthenticationFilter
//     }
// }