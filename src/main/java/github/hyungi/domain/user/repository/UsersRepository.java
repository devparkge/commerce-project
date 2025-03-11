package github.hyungi.domain.user.repository;

import github.hyungi.domain.user.model.User;

public interface UsersRepository extends UsersReadRepository {
    User save(User user);
}
