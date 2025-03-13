package github.hyungi.infrastructure.jpa.users.repository;

import github.hyungi.domain.user.model.User;
import github.hyungi.domain.user.repository.UsersReadRepository;
import github.hyungi.infrastructure.jpa.users.dao.UsersDao;
import github.hyungi.infrastructure.jpa.users.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UsersReadRepositoryImpl implements UsersReadRepository {
    protected final UsersDao usersDao;

    @Override
    public Optional<User> findByEmail(String email) {
        return usersDao.findByEmail(email).map(UsersEntity::toDomain);
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return usersDao.findById(userId).map(UsersEntity::toDomain);
    }
}
