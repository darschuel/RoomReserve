# Woche 2 - Domain Layer

## Zielsetzung  
In woche 2 wurde ein robuster, konsistenter Domain Layer aufgebaut, der 1:1 mit dem bestehenden PostgreSQL-Schema übereinstimmt und als technisches Fundament für Authentifizierung, Autorisierung und Reservierungslogik dient.  

### Bewusst ausgeschlossen:
- REST-Endpunkte
- UI/Frontend  

### Fokus dieser Woche:
- saubere JPA-Entity-Modellierung
- klare Verantwortlichkeitstrennung
- schema-sichere Projektbasis

## Datenbank und Schema-Management
- **Datenbank:** PostgreSQL
- **Schema-Versionierung:** Flyway
- **ORM:** Hibernate

### Flyway-Migrationen
- **V1 - Init**    
Grundlegendes Datenbank-Setup
- **V2 - erd_v1**  
Erstellung aller Tabellen gemäss ERD
- **V3 - seed_roles**  
Initiale Rollen (USER, ADMIN)

### Hibernate-Konfiguration  
Hibernate läuft im Validierungsmodus.  
**Konsequenz:**  
- Hibernate prüft beim Start ausschliesslich, ob Entities und Schema übereinstimmen
- Keine automatische Schema-Erzeugung oder -Anpassung
- Alle Änderungen erfolgen kontrolliert über Flyway  

Das erzwingt Disziplin und verhindert "Schema-Drift". 

## Domain Entities
### Implementierte Kern-Entities
- AppUser
- Role
- Room
- Reservation
- RefreshToken
- AuditLog

## Architektur- und Designentscheidungen
- UUIDS für alle Hauptentitäten: sicher, eindeutig, API-tauglich
- Enums als VARCHAR: lesbar, migrationssicher, keine ordinalen Fallen
- Refresh Tokens mit Self-Reference: saubere Token-Rotation möglich
- Keine Schema-Generierung durch Hibernate: die Datenbankstruktur wird ausschliesslich über Flyway verwaltet

## Repository Layer
### Implementierte Repositories
- AppUserRepository
- RoleRepository
- RefreshTokenRepository

### Technologie
- Quarkus Panache Repository

### Ziel
- minimaler Boilerplate
- klare, deklarative Datenzugriffe
- saubere Grundlage für den kommenden Service Layer

## Qualitätssicherung  
**mvn test**  
Tests laufen erfolgreich und validieren die Konsistenz zwischen Domain Layer und Datenbankschema.

## Fazit  
Der Domain Layer ist sauber modeliert, schema-konsistent und erweiterungsfähig.  
Das Fundament steht. Ab hier kann ohne technisches Chaos auf Services, Security und REST aufgebaut werden.
