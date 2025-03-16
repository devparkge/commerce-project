package github.hyungi.domain.cart.model;

import github.hyungi.domain.product.model.Product;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public record Item(
        UUID itemId,
        Product product,
        BigDecimal totalPrice,
        Instant createdAt,
        Instant updatedAt
) {
    public static Item create(Product product, int stock) {
        BigDecimal totalPrice = product.price().multiply(BigDecimal.valueOf(stock));
        return Item.builder()
                .itemId(UUID.randomUUID())
                .product(product)
                .totalPrice(totalPrice)
                .build();
    }
}
