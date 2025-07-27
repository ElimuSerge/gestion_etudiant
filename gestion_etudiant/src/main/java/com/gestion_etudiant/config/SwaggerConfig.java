package com.gestion_etudiant.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestion des Étudiants")
                        .version("V.1.0.0")
                        .description("Documentation interactive pour l'API REST de gestion des étudiants, développée par le group 1."))
                .addServersItem(new Server().url("http://localhost:8080").description("Serveur local"));
    }
}