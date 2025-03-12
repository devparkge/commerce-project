package github.hyungi.infrastructure.jpa.users.dao;

import github.hyungi.infrastructure.jpa.users.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsersDao extends JpaRepository<UsersEntity, UUID> {
    Optional<UsersEntity> findByEmail(String email);
}
