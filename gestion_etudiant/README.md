PROJETS REST API
--------------------------
Améilorez vos projets pour y intégrer les éléments suivants:


1. API Design

- Use RESTful naming conventions
a.Votre EtudiantController suit déjà les conventions RESTful (par ex., /api/etudiants pour les ressources, verbes HTTP corrects comme GET, POST, etc.). Aucun changement majeur n'est nécessaire ici.

- Plan versioning (e.g., /api/v1/)
b. Le @RequestMapping est passé de /api/etudiants à /api/v1/etudiants pour intégrer le versionnement.
Lister les étudiants : GET http://localhost:8080/api/v1/etudiants
Créer un étudiant : Envoie une requête POST à http://localhost:8080/api/v1/etudiants{"nom": "Doe","prenom": "John",
  "email": "john.doe@example.com" }
Vérifier un étudiant : GET http://localhost:8080/api/v1/etudiants/1

2. Validation & Error Handling
a. Validation : Cela signifie vérifier que les données envoyées à ton API (par exemple, le nom, prénom ou email d’un étudiant) sont correctes avant de les traiter. Par exemple, tu ne veux pas enregistrer un étudiant avec un email invalide ou un nom vide.

b. Error Handling : Quand quelque chose ne va pas (par exemple, un étudiant n’est pas trouvé ou les données sont incorrectes), ton API doit renvoyer une réponse claire avec un code HTTP approprié (comme 404 pour "non trouvé" ou 400 pour "mauvaise requête") et un message compréhensible.

- Valider les entrées (avec des DTO) :
     Utiliser un objet spécifique (DTO) pour recevoir les données des requêtes, avec des règles de validation.
    Exemple : Si un client envoie un JSON avec un email invalide, le DTO avec des annotations comme @NotBlank : Vérifie que le champ n’est ni vide ni null. ou @Email: Vérifie que l’email a un format valide (par ex., john.doe@example.com).sinon bloquera la requête avant qu’elle n’atteigne la base de données.
    
- Retourner des codes HTTP significatifs :
     201 Created : Quand un nouvel étudiant est créé.
      200 OK : Quand une requête (comme lister ou obtenir un étudiant) réussit.
      404 Not Found : Quand un étudiant n’existe pas.
      400 Bad Request : Quand les données envoyées sont invalides.

- Format de réponse d’erreur cohérent :
     Créer une structure standard pour toutes les erreurs (par exemple, un JSON avec un message et un code).
    {
      "status": 404,
      "message": "Étudiant non trouvé",
      "timestamp": 1690714567890
    }
3. Authentication & Authorization

  A. Authentication (Authentification) : C’est vérifier l’identité de l’utilisateur qui fait une requête à ton API. Par exemple, s’assurer que la personne qui essaie d’ajouter un étudiant est bien un utilisateur valide (avec un nom d’utilisateur et un mot de passe, ou un token).

  B. Authorization (Autorisation) : C’est déterminer ce que cet utilisateur a le droit de faire. Par exemple, seuls les utilisateurs avec un rôle “ADMIN” peuvent ajouter ou supprimer des étudiants, tandis qu’un utilisateur “USER” pourrait seulement voir la liste.

- Utiliser JWT, OAuth2, ou des clés API : On va utiliser JWT (JSON Web Token), pour sécuriser une API REST.
      Un JWT est comme un “billet” numérique que l’utilisateur envoie avec chaque requête pour prouver son identité.        
        - L’utilisateur se connecte avec le nom et mot de passe {"username": "admin", "password": "admin123"} (par ex., via un endpoint http://localhost:8080/api/v1/auth/login).
        Le serveur génère un JWT (une chaîne de caractères) contenant des informations comme le nom d’utilisateur et le rôle (par ex., “ADMIN”). (eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc1MzY5MzY1NCwiZXhwIjoxNzUzNzgwMDU0fQ.1RUabrbAe7h47ftu5cX3GvhSkfjFankXaixxUFyTjZ81h1-pYala47zw0s1dzU3O)

        L’utilisateur inclut ce JWT dans l’en-tête de chaque requête (par ex., Authorization: Bearer <token>).
        Le serveur vérifie le JWT pour s’assurer qu’il est valide et autorise l’accès.
        
- Définir des rôles et permissions : On va configurer deux rôles : “ADMIN” (qui peut tout faire) et “USER” (qui peut seulement lire les données).
    On va définir deux rôles :
        ADMIN : Peut créer, modifier, supprimer, et lister les étudiants.
        USER : Peut seulement lister et voir les étudiants.
        On configurera l’API pour que certaines actions (comme POST, PUT, DELETE) nécessitent le rôle ADMIN.

4. Logging & Monitoring :
  a. Logging : Ajouter des logs détaillés pour permettre de suivre les actions dans l’application( par ex., requêtes HTTP, erreurs, opérations sur la base de données) en utilisant SLF4J (déjà inclus via Spring Boot).

  b.Spring Boot Actuator : C’est un outil inclus dans Spring Boot qui ajoute des endpoints (URLs) pour vérifier l’état de ton application. Par exemple :
        1. GET http://localhost:8080/actuator/health : Vérifie si l’application et la base de données sont opérationnelles.
          Résultat attendu dans JSON une fois lancer la requete dans POSTMAN :
          {
            "status": "UP",
            "components": {
              "db": { "status": "UP", ... },
              "diskSpace": { "status": "UP", ... },
              "ping": { "status": "UP" }
            }
          }
        2. GET http://localhost:8080/actuator/metrics : Montre des statistiques comme le nombre de requêtes HTTP ou l’utilisation de la mémoire.
            Résultat attendu dans JSON une fois lancer la requete dans POSTMAN :
            {
              "names": [
                  "application.ready.time",
                  "application.started.time",
                  "disk.free",
                  "disk.total",
                  "executor.active",
                  "executor.completed",        
                  "jvm.compilation.time",
                  "jvm.gc.live.data.size",
                  "jvm.gc.max.data.size",
                  "jvm.gc.memory.allocated",
                  "jvm.gc.memory.promoted",
                  "jvm.gc.overhead",
                  "jvm.gc.pause",
                  "jvm.info",
                  "jvm.memory.committed",
                  "jvm.memory.max",
                  "jvm.memory.usage.after.gc",
                  "jvm.memory.used",
                  "jvm.threads.daemon",        
                  "spring.security.filterchains.access.exceptions.before",
                  "spring.security.filterchains.active",
                  "spring.security.filterchains.authentication.anonymous.after",
                  "spring.security.filterchains.authentication.anonymous.before",
                  
              ]
          }
        3. GET http://localhost:8080/actuator/prometheus : ..PRENDS TOUTS CE STATISTQUES ET LE Fournit dans un format que Prometheus peut lire.
            Résultat attendu dans JSON une fois lancer la requete dans POSTMAN :
            # HELP http_server_requests_active_seconds_max  
            # TYPE http_server_requests_active_seconds_max gauge
            http_server_requests_active_seconds_max{application="gestion_etudiant",exception="none",method="GET",outcome="SUCCESS",status="200",uri="UNKNOWN"} 1.3839064
            http_server_requests_active_seconds_max{application="gestion_etudiant",exception="none",method="POST",outcome="SUCCESS",status="200",uri="UNKNOWN"} 0.0

- Log requests/responses/errors (e.g., with Logback, ELK)
      Ajouté Logger avec LoggerFactory pour tracer les tentatives de connexion réussies et échouées.
      Utilisé logger.info pour les connexions réussies et logger.warn pour les échecs.
      On va améliorer les logs dans les fichiers existants (EtudiantController.java).
      Dans POST MAN Envoie une requête : POST http://localhost:8080/api/v1/auth/login avec les donnees json correcte : {
  "username": "admin","password": "admin123"}..et si tu envoies les donnees incorecte par exemple : {"username": "admin",
  "password": "wrong"}  si l'on entre dans e dossier log verifier le fichier {app.log} on aura des trucs du genre : 
          2025-07-27 23:36:00 [http-nio-8080-exec-1] INFO  com.gestion_etudiant.controller.AuthController - Tentative de connexion pour l'utilisateur : admin
          2025-07-27 23:36:00 [http-nio-8080-exec-1] INFO  com.gestion_etudiant.controller.AuthController - Token JWT généré pour l'utilisateur ADMIN : admin
          2025-07-27 23:36:05 [http-nio-8080-exec-2] INFO  com.gestion_etudiant.controller.AuthController - Tentative de connexion pour l'utilisateur : admin
          2025-07-27 23:36:05 [http-nio-8080-exec-2] WARN  com.gestion_etudiant.controller.AuthController - Échec de la connexion pour l'utilisateur : admin, identifiants invalides

- Use tools like Prometheus, Grafana, or New Relic
    Télécharger et installer Prometheus DEPUIS Le prometheus.io/download

5. Testing
- Unit tests (e.g., JUnit)
- Integration tests (e.g., TestRestTemplate)
- API testing (e.g., Postman, Newman)

6.Documentation
. Swagger/OpenAPI

7. Deployment
- Containerize with Docker
- CI/CD with Jenkins
- Host on services like Render, etc.

8. Security Best Practices
- Sanitize inputs to avoid SQL/XSS attacks
- Use HTTPS
- Implement rate limiting/throttling

http://localhost:8080/actuator/prometheus
GET http://localhost:8080/actuator/health : 