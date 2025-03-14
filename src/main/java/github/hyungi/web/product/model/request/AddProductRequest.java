package github.hyungi.web.product.model.request;

import java.math.BigDecimal;

public record AddProductRequest(
        String name,
        String description,
        BigDecimal price,
        int stock,
        String category
) {
}
