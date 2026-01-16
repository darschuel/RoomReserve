-- Roles
CREATE TABLE role (
    id SMALLSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- User, Role (M:N)
CREATE TABLE user_role (
    user_id UUID NOT NULL,
    role_id SMALLINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_role_user
    FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_role_role
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE RESTRICT
);

-- Rooms
CREATE TABLE room (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(255) NOT NULL UNIQUE,
    capacity INT,
    is_active BOOLEAN NOT NULL DEFAULT true,
    inactive_reason TEXT,
    inactive_until TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Reservations
CREATE TABLE reservation (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    room_id UUID NOT NULL,
    user_id UUID NOT NULL,
    start_at TIMESTAMPTZ NOT NULL,
    end_at TIMESTAMPTZ NOT NULL,
    status VARCHAR(30) NOT NULL,
    canceled_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT fk_reservation_room
        FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE RESTRICT,
    CONSTRAINT fk_reservation_user
        FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE RESTRICT,
    CONSTRAINT chk_reservation_time
        CHECK (end_at > start_at)
);

CREATE INDEX idx_reservation_room_time
    ON reservation (room_id, start_at, end_at);

CREATE INDEX idx_reservation_user_time
    ON reservation (user_id, start_at);

-- Refresh Tokens
CREATE TABLE refresh_token (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id UUID NOT NULL,
    token_hash VARCHAR(255) NOT NULL,
    issued_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    expires_at TIMESTAMPTZ NOT NULL,
    revoked_at TIMESTAMPTZ,
    replaced_by UUID,
    created_by_ip VARCHAR(100),
    CONSTRAINT fk_refresh_user
        FOREIGN KEY (user_id) REFERENCES app_user(id) ON DELETE CASCADE,
    CONSTRAINT fk_refresh_replaced
        FOREIGN KEY (replaced_by) REFERENCES refresh_token(id) ON DELETE SET NULL
);

CREATE INDEX idx_refresh_user ON refresh_token (user_id);
CREATE INDEX idx_refresh_expires ON refresh_token (expires_at);

-- Audit Log
CREATE TABLE audit_log (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    actor_user_id UUID,
    event_type VARCHAR(100) NOT NULL,
    entity_type VARCHAR(100) NOT NULL,
    entity_id UUID,
    details_json JSONB,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT fk_audit_actor
       FOREIGN KEY (actor_user_id) REFERENCES app_user(id) ON DELETE SET NULL
);
