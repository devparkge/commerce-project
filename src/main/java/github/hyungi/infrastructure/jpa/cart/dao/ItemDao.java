package github.hyungi.infrastructure.jpa.cart.dao;

import github.hyungi.infrastructure.jpa.cart.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemDao extends JpaRepository<ItemEntity, UUID> {
}
