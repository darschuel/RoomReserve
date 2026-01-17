package ch.diegoaraujo.roomreserve.repository;

import ch.diegoaraujo.roomreserve.domain.AppUser;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AppUserRepository implements PanacheRepository<AppUser> {

    public Optional<AppUser> findByEmail(String email) {
        return find("email", email).firstResultOptional();
    }

    public Optional<AppUser> findById(UUID id) {
        return find("id", id).firstResultOptional();
    }
}
