package github.hyungi.infrastructure.jpa.repository;

import github.hyungi.domain.user.model.User;
import github.hyungi.domain.user.repository.UsersRepository;
import github.hyungi.infrastructure.jpa.dao.UsersDao;
import github.hyungi.infrastructure.jpa.entity.UsersEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepositoryImpl extends UsersReadRepositoryImpl implements UsersRepository {

    public UsersRepositoryImpl(UsersDao usersDao) {
        super(usersDao);
    }

    @Override
    public User save(User user) {
        return usersDao.save(UsersEntity.from(user)).toDomain();
    }
}
