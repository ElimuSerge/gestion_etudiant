# Utiliser une image de base OpenJDK 17
FROM openjdk:17-jdk-slim

VOLUME /tmp

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR généré par Maven
COPY target/gestion_etudiant-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port 8080 (port par défaut de Spring Boot)
EXPOSE 8080



# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]