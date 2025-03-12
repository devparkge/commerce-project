package github.hyungi.infrastructure.jpa.product.dao;

import github.hyungi.infrastructure.jpa.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductDao extends JpaRepository<ProductEntity, UUID> {

}
