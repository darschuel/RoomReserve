package ch.diegoaraujo.roomreserve.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, length = 255)
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "inactive_reason")
    private String inactiveReason;

    @Column(name = "inactive_until")
    private OffsetDateTime inactiveUntil;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean active) { isActive = active; }

    public String getInactiveReason() { return inactiveReason; }
    public void setInactiveReason(String inactiveReason) { this.inactiveReason = inactiveReason; }

    public OffsetDateTime getInactiveUntil() { return inactiveUntil; }
    public void setInactiveUntil(OffsetDateTime inactiveUntil) { this.inactiveUntil = inactiveUntil; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
