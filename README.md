# Gestion des Drones - Sprint 03

- `backend/`  : API REST Spring Boot + MySQL (`backend/sql/drones_db.sql`)
- `frontend/` : application Angular (appelle l'API avec HttpClient)

## Lancer le backend
1. Démarrer MySQL (XAMPP ou service MySQL).
2. Vérifier `backend/src/main/resources/application.properties` (username / password MySQL).
3. Lancer `DronesApplication` (VS Code / IntelliJ) ou, dans `backend/` : `mvn spring-boot:run`
4. Tester : http://localhost:8080/api/drones

## Lancer le frontend
Dans `frontend/` : `npm install` puis `npm start` -> http://localhost:4200
