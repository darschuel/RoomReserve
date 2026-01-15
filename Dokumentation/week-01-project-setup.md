# Woche 01 – Projekt-Setup & Fundament

## Ziel
Aufbau eines sauberen und reproduzierbaren Projektfundaments mit Versionskontrolle, Backend-Framework, Datenbank und Migrationen.

Der Fokus lag bewusst auf **Infrastruktur und Korrektheit**, nicht auf Features.

---

## Umgesetzte Punkte

### Repository & Struktur
- GitHub-Repository initialisiert
- Klare Projektstruktur:
  - `backend/` für die Quarkus-Applikation
  - `docs/` für Dokumentation
  - `docker-compose.yml` im Projekt-Root
- `.gitignore` für IntelliJ und macOS konfiguriert

### Backend
- Quarkus-Projekt mit Maven erstellt
- Einheitliche Java-Version **Java 21 (LTS)** für Java und Maven
- Health-Endpoint verfügbar (`/health`)
- OpenAPI & Swagger UI aktiviert (`/q/swagger-ui/`)

### Datenbank
- PostgreSQL über Docker Compose gestartet
- Anbindung der Applikation an die Datenbank über Quarkus-Datasource

### Migrationen
- Flyway aktiviert und konfiguriert
- Erste Migration (`V1__init.sql`) erstellt
- Initiale Tabelle `app_user` über Flyway angelegt
- Hibernate auf `validate` gesetzt, um Schema-Konsistenz sicherzustellen

---

## Probleme & Lösungen

### Hibernate-Validierungsfehler
- Quarkus startete nicht wegen einer Template-Entity (`MyEntity`) ohne entsprechende Tabelle
- Ursache: Hibernate-Validierung ohne passende Migration

**Lösung:**
- Entfernen der Template-Entity
- Flyway als einzige Quelle für Schema-Änderungen beibehalten

Dadurch wurde ein sauberer und konsistenter Start sichergestellt.

---

## Bewusst nicht umgesetzt
- Keine Business-Logik
- Keine produktiven Entities
- Keine Authentifizierungslogik

Dies wurde bewusst ausgelassen, um Infrastruktur und Features nicht zu vermischen.

---

## Ergebnis
- Quarkus startet erfolgreich
- PostgreSQL läuft über Docker
- Flyway-Migrationen werden beim Start ausgeführt
- Das Projekt ist bereit für ERD-Design und Authentifizierung in Woche 2

