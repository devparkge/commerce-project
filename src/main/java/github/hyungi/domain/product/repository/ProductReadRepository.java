package github.hyungi.domain.product.repository;

import github.hyungi.domain.product.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductReadRepository {
    List<Product> findAll();
    Optional<Product> findById(UUID productId);
}
