package github.hyungi.domain.user.repository;

import github.hyungi.domain.user.model.User;

import java.util.Optional;

public interface UsersReadRepository {
    Optional<User> findByEmail(String email);
}
