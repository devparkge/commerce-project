package github.hyungi.domain.repository;

import github.hyungi.domain.model.User;

public interface UsersRepository extends UsersReadRepository {
    User save(User user);
}
