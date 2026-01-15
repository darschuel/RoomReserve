!! THIS PLAN WAS CORRECTED BY CHATGPT FOR A BETTER UNDERSTANDING !!

Projektplan: RoomReserve 

Woche 1 — Setup, Struktur, DB-Fundament
Ziel: Projekt läuft lokal, DB ist angebunden, Migrations-Setup steht, Doku startet.
Deliverables
* Git Repo + README (Titel, Ziel, Scope, Stack, Start-Anleitung)
* Quarkus Projekt erstellt + läuft lokal
* PostgreSQL via Docker Compose
* Migrations eingerichtet (Flyway empfohlen)
* ERD v1: Users, Roles, Rooms, Reservations, RefreshTokens, AuditLog
Definition of Done
* /q/health (oder /health) ok
* DB Connection ok
* Erste Migration läuft (Tabellen minimal vorhanden)

Woche 2 — Auth Basis (Register/Login) + RBAC-Grundlage
Ziel: Benutzer können sich registrieren/einloggen, Rollen funktionieren, API ist sauber.
Features
* Register (bcrypt hashing)
* Login (JWT Access Token)
* Rollen: USER/ADMIN (Seed)
* DTOs + Validation
* Global Error Handling (standardisierte Fehlerantworten)
Deliverables
* OpenAPI/Swagger zeigt Auth Endpoints
* Seed: 1 Admin + 1 User
Definition of Done
* Login liefert Access Token
* Protected Endpoint funktioniert (z.B. /me)

Woche 3 — Refresh Tokens + Rotation + Logout (Resume-Grade Auth)
Ziel: Auth ist professionell, nicht “Tutorial-JWT”.
Features
* Refresh Token Flow (Access erneuern)
* Refresh Tokens serverseitig in DB speichern
* Refresh Token Rotation (fix)
    * Refresh → neuer Refresh Token
    * Alter Refresh Token → revoked
* Logout = Refresh Token widerrufen
* Optional (stark): Reuse Detection (alter revoked Token wird genutzt → Session kill)
Deliverables
* Doku: Auth Flow (Access/Refresh/Logout)
* Tests Token-Service (mind. 2–3)
Definition of Done
* Access abgelaufen → Refresh funktioniert
* Logout killt Refresh (Refresh danach nicht mehr gültig)

Woche 4 — Rooms (Admin CRUD) + Deactivate/Reactivate (statt Delete)
Ziel: Admin kann Räume verwalten; User kann lesen; Soft-Delete mit Grund ist drin.
Features
* Admin: Create/Update Room
* Admin: Deactivate Room (mit reason, optional until)
* Admin: Reactivate Room
* User: List Rooms, Room Details
* Pagination/Filter minimal (z.B. name contains, optional active=true)
Deliverables
* DTOs sauber getrennt
* Standardisierte Error Responses
Definition of Done
* RBAC greift (User kommt nicht auf Admin Endpoints)
* Room Deactivate speichert reason und ist in Responses sichtbar

Woche 5 — Reservations Kernlogik + Room-Status-Regeln
Ziel: Reservieren ist korrekt, verhindert Konflikte, respektiert Deactivate.
Features
* Create Reservation (USER)
* List “my reservations”
* Cancel Reservation (Policy: z.B. bis 2h vorher)
* Konfliktcheck: keine Überschneidung
    * start < existingEnd AND end > existingStart
* Block Reservation wenn Room INACTIVE
    * Fehlerantwort enthält reason (und optional until)
Definition of Done
* Doppelbuchung verhindert
* Inactive Room kann nicht gebucht werden, reason kommt zurück

Woche 6 — Transaktionen, Hardening, Audit Log, Rate Limiting
Ziel: Stabilität + Security-Signal.
Features
* Booking in Transaktion (atomar)
* AuditLog Events:
    * CREATE_RESERVATION, CANCEL_RESERVATION
    * ADMIN_ROOM_UPDATE, ADMIN_ROOM_DEACTIVATE, ADMIN_ROOM_REACTIVATE
* Rate limiting (mindestens Login/Refresh)
* Security Checks auf allen kritischen Endpoints
Deliverables
* README Abschnitt: Security Notes (Threats & Mitigations)
Definition of Done
* Fehlerfälle sind sauber (kein Happy-Path-only)
* AuditLog wird zuverlässig geschrieben

Woche 7 — Tests (Pflicht) + Mini UI (optional, klein halten)
Ziel: Qualität + Klick-Demo (nur falls Zeit).
Pflicht: Service-Layer Tests
* Overlap/Conflict
* Cancel Policy
* Room inactive blocks reservation
* Token rotation (mind. 1 Test)
Optional Mini-UI (React)
* Login
* Rooms list (zeigt active/inactive + reason)
* Reserve form
* My reservations
Definition of Done
* Tests laufen lokal (optional GitHub Actions)

Woche 8 — Polishing, Doku, Demo, Optional Deploy
Ziel: CV-ready, in 10 Minuten verständlich/testbar.
Deliverables
* README final:
    * Problem
    * Features
    * Architektur (Package Struktur)
    * ERD Bild
    * Auth Flow
    * API Beispiele
    * Known limitations / Next steps
* Swagger sauber
* Seed Data & Demo Steps
* Optional Deploy oder Docker Run Anleitung
Definition of Done
* Dritter kann es in ≤10 Minuten starten und testen


