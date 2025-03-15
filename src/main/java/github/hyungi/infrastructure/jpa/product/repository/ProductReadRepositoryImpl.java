package github.hyungi.infrastructure.jpa.product.repository;

import github.hyungi.domain.product.model.Product;
import github.hyungi.domain.product.repository.ProductReadRepository;
import github.hyungi.infrastructure.jpa.product.dao.ProductDao;
import github.hyungi.infrastructure.jpa.product.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductReadRepositoryImpl implements ProductReadRepository {
    private final ProductDao productDao;

    @Override
    public List<Product> findAll() {
        return productDao.findAll().stream()
                .map(ProductEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return productDao.findById(productId).map(ProductEntity::toDomain);
    }
}
