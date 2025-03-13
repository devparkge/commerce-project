package github.hyungi.web.product.model.response;

import github.hyungi.domain.product.model.Product;
import github.hyungi.web.model.Seller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


public record ProductResponse(
        UUID productId,
        Seller seller,
        String name,
        String description,
        BigDecimal price,
        int stock,
        String category,
        String imageUrl,
        Instant createdAt
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.productId(),
                Seller.from(product.seller()),
                product.name(),
                product.description(),
                product.price(),
                product.stock(),
                product.category(),
                product.imageUrl(),
                product.createdAt()
        );
    }
}
