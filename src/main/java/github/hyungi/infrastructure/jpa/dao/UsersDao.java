package github.hyungi.infrastructure.jpa.dao;

import github.hyungi.infrastructure.jpa.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersDao extends JpaRepository<UsersEntity, UUID> {
}
