// package com.gestion_etudiant.config;

// import io.github.bucket4j.Bandwidth;
// import io.github.bucket4j.Bucket;
// import org.springframework.stereotype.Component;

// import java.time.Duration;

// @Component
// public class RateLimiterConfig {
//     public Bucket createBucket() {
//         Bandwidth limit = Bandwidth.simple(10, Duration.ofMinutes(1));
//         return Bucket.builder().addLimit(limit).build();
//     }
// }

