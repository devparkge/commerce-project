package github.hyungi.domain.product.service;

import github.hyungi.domain.product.model.Product;
import github.hyungi.domain.product.repository.ProductRepository;
import github.hyungi.exception.NotFoundUUIDException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(UUID productId) {
        return productRepository.findById(productId).orElseThrow(NotFoundUUIDException::new);
    }

}
