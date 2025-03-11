package github.hyungi.domain.product.model;

import github.hyungi.domain.user.model.User;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
public record Product(
        UUID productId,
        User seller,
        String name,
        String description,
        BigDecimal price,
        int stock,
        String category,
        String imageUrl,
        Instant createdAt,
        Instant updatedAt
) {
    public static Product create(
            User seller,
            String name,
            String description,
            BigDecimal price,
            int stock,
            String category,
            String imageUrl
    ) {
        return Product.builder()
                .productId(UUID.randomUUID())
                .seller(seller)
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .category(category)
                .imageUrl(imageUrl)
                .build();
    }
}
