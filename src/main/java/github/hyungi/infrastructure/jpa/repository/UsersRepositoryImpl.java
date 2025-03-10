package github.hyungi.infrastructure.jpa.repository;

import github.hyungi.domain.model.User;
import github.hyungi.domain.repository.UsersRepository;
import github.hyungi.infrastructure.jpa.dao.UsersDao;
import github.hyungi.infrastructure.jpa.entity.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {
    private final UsersDao usersDao;

    @Override
    public User save(User user) {
        return usersDao.save(UsersEntity.from(user)).toDomain();
    }
}
