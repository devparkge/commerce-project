package github.hyungi.domain.user.repository;

import github.hyungi.domain.user.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UsersReadRepository {
    Optional<User> findByEmail(String email);
    Optional<User> findById(UUID userId);
}
