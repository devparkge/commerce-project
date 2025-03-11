package github.hyungi.infrastructure.jpa.repository;

import github.hyungi.domain.model.User;
import github.hyungi.domain.repository.UsersReadRepository;
import github.hyungi.infrastructure.jpa.dao.UsersDao;
import github.hyungi.infrastructure.jpa.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsersReadRepositoryImpl implements UsersReadRepository {
    protected final UsersDao usersDao;

    @Override
    public Optional<User> findByEmail(String email) {
        return usersDao.findByEmail(email).map(UsersEntity::toDomain);
    }
}
