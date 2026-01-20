# ERD v1

## App_User
- id (UUID)
- email (VARCHAR, Unique, Not Null)
- password_hash (VARCHAR, Not Null)
- created_at (TIMESTAMPZ, Not Null)  
### Beziehungen
- 1:N zu Reservation, Ein User kann mehrere Reservierungen haben
- 1:N zu Refresh_Token, Ein User kann mehrere Refresh Tokens besitzen
- M:N zu Role, über USER_ROLE
- 1:N zu AUDIT_LOG, als Actor

## Role
- id (SMALLINT)
- name (VARCHAR, Unique, Not Null)  
### Beziehungen
- M:N zu APP_USER, über USER_ROLE

## Room
- id (UUID) 
- name (VARCHAR, Unique, Not Null)
- capacity (INT)
- is_active (Boolean, Not Null)
- inactive_reason (Text)
- inactive_until (TIMESTAMPZ)
- created_at (TIMESTAMPZ, Not Null)  
### Beziehungen
- 1:N zu Reservation, Ein Raum kann mehrere Reservationen haben

## User_Role
- user_id (UUID)
- role_id (SMALLINT)  
### Beziehungen
- N:1 zu APP_USER
- N:1 zu ROLE

## Reservation
- id (UUID)
- start_at (TIMESTAMPZ, Not Null)
- end_at (TIMESTAMPZ, Not Null)
- status (VARCHAR, Not Null,)
- canceled_at (TIMESTAMPZ)
- created_at (TIMESTAMPZ, Not Null)  
### Beziehungen
- N:1 zu APP_USER
- N:1 zu ROOM

## Refresh_Token
- id (UUID)
- issued_at (TIMESTAMPZ, Not Null)
- expires_at (TIMESTAMPZ, Not Null)
- token_hash (VARCHAR, Not Null)
- revoked_at (TIMESTAMPZ)
- created_by_ip (VARCHAR)  
### Beziehungen
- N:1 zu APP_USER
- 1:1 zu REFRESH_TOKEN, Self-Reference für Rotation

## AUDIT_LOG 
- id (UUID)
- event_type (VARCHAR, Not Null)
- entity_type (VARCHAR, Not Null)
- entity_id (UUID)
- details_json (JSONB)
- created_at (TIMESTAMPZ, Not Null)  
### Beziehung
- N:1 zu APP_USER
