package github.hyungi.domain.repository;

import github.hyungi.domain.model.User;

import java.util.Optional;

public interface UsersReadRepository {
    Optional<User> findByEmail(String email);
}
