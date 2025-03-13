package github.hyungi.web.product.api;

import github.hyungi.annotation.JwtAuthentication;
import github.hyungi.domain.product.model.Product;
import github.hyungi.domain.product.service.AddProductService;
import github.hyungi.web.product.model.request.AddProductRequest;
import github.hyungi.web.product.model.response.ProductResponse;
import github.hyungi.web.product.model.response.wrapper.ProductResponseWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductApiController {
    private final String imageUploadDir;
    private final AddProductService addProductService;

    public ProductApiController(@Value("${image.upload-dir}") String imageUploadDir, AddProductService addProductService) {
        this.imageUploadDir = imageUploadDir;
        this.addProductService = addProductService;
    }

    @PutMapping("/product-add")
    public ProductResponseWrapper addProduct(
            @JwtAuthentication UUID userId,
            @RequestBody AddProductRequest addProductRequest
    ) throws IOException {
        String fileName = UUID.randomUUID() + "_" + addProductRequest.image().getOriginalFilename();
        Path imagePath = Paths.get(imageUploadDir).resolve(fileName);

        addProductRequest.image().transferTo(imagePath.toFile());

        Product product = addProductService.addProduct(
                userId,
                addProductRequest.name(),
                addProductRequest.description(),
                addProductRequest.price(),
                addProductRequest.stock(),
                addProductRequest.category(),
                fileName
        );
        return ProductResponseWrapper.create(ProductResponse.from(product));
    }
}
