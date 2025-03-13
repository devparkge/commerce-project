package github.hyungi.web.product.model.request;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record AddProductRequest(
        String name,
        String description,
        BigDecimal price,
        int stock,
        String category,
        MultipartFile image
) {
}
