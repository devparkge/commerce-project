package github.hyungi.domain.product.repository;

import github.hyungi.domain.product.model.Product;

public interface ProductRepository {
    Product save(Product product);
}
