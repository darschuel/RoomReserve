package ch.diegoaraujo.roomreserve.repository;

import ch.diegoaraujo.roomreserve.domain.RefreshToken;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class RefreshTokenRepository implements PanacheRepository<RefreshToken> {

    public Optional<RefreshToken> findById(UUID id) {
        return find("id", id).firstResultOptional();
    }
}
