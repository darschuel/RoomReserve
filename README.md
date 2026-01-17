# RoomReserve

RoomReserve is a secure room booking system focused on backend architecture, security (JWT + refresh token rotation), and solid database design.

## Scope
- Users can view rooms and create/cancel reservations
- Admins can manage rooms and deactivate/reactivate rooms with a reason
- Authentication with JWT access tokens + refresh tokens (rotation)
- PostgreSQL + Flyway migrations
- OpenAPI/Swagger documentation

## Tech Stack
- Java, Quarkus
- PostgreSQL
- Flyway
- JWT (Access + Refresh), RBAC (USER/ADMIN)

## Roadmap
See 'PLAN.md'.

# Getting Started (lokales Setup)

## Kurzüberblick an Architektur
- Backend: Java 21, Quarkus
- Persistence: JPA (Hibernate ORM)
- Datenbank: PostgreSQL 
- Migrationen: FlyWay
- Security: JWT (Access + Refresh Token mit Rotation)
- API: REST + OpenAPI (Swagger)


## Voraussetzungen
- Java 21 (Ich empfehle Amazon Coretto)
- Docker und Docker Compose
- Maven 3.9+

## Repo klonen
git clone https://github.com/darschuel/RoomReserve.git  
cd RoomReserve

## Datenbank starten
docker compose up -d  
startet einen PostgreSQL 16 Instanz für lokale Entwicklung

## Backend starten
cd backend/roomreserve
mvn quarkus:dev

## API und Swagger testen
- API User URL: http://localhost:8080
- Swagger UI: http://localhost:8080/q/swagger-ui

## Datenbank und Migration
- Schema-Management via FlyWay
- Migrationen liegen unter: backend/roomreserve/src/main/resources/db/migration
- Beim Start werden alle Migrationen automatisch validiert und ausgeführt

## Test Ausführen
mvn test



