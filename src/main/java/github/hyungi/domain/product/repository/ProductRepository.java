package github.hyungi.domain.product.repository;

import github.hyungi.domain.product.model.Product;

public interface ProductRepository extends ProductReadRepository {
    Product save(Product product);
}
