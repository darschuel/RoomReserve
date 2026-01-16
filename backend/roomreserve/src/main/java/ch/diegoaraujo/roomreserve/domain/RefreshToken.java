package ch.diegoaraujo.roomreserve.domain;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "token_hash", nullable = false, length = 255)
    private String tokenHash;

    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @Column(name = "expires_at", nullable = false)
    private OffsetDateTime expiresAt;

    @Column(name = "revoked_at")
    private OffsetDateTime revokedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replaced_by")
    private RefreshToken replacedBy;

    @Column(name = "created_by_ip", length = 100)
    private String createdByIp;

    public UUID getId(){return id;}
    public void setId(UUID id){this.id = id;}

    public String getTokenHash(){return tokenHash;}
    public void setTokenHash(String tokenHash){this.tokenHash = tokenHash;}

    public OffsetDateTime getIssuedAt(){return issuedAt;}
    public void setIssuedAt(OffsetDateTime issuedAt){this.issuedAt = issuedAt;}

    public OffsetDateTime getExpiresAt(){return expiresAt;}
    public void setExpiresAt(OffsetDateTime expiresAt){this.expiresAt = expiresAt;}

    public OffsetDateTime getRevokedAt(){return revokedAt;}
    public void setRevokedAt(OffsetDateTime revokedAt){this.revokedAt = revokedAt;}

    public String getCreatedByIp(){return createdByIp;}
    public void setCreatedByIp(String createdByIp){this.createdByIp = createdByIp;}

    public RefreshToken getReplacedBy(){return replacedBy;}
    public void setReplacedBy(RefreshToken refreshToken){this.replacedBy = refreshToken;}

    public AppUser getUser() {return user;}
    public void setUser(AppUser user){this.user = user;}
}
