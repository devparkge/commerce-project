package github.hyungi.web.product.model.response.wrapper;

import github.hyungi.web.product.model.response.ProductResponse;

public record ProductResponseWrapper(
        ProductResponse product
) {
    public static ProductResponseWrapper create(ProductResponse product) {
        return new ProductResponseWrapper(product);
    }
}
