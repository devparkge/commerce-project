package github.hyungi.domain.repository;

import github.hyungi.domain.model.User;

public interface UsersRepository {
    User save(User user);
}
