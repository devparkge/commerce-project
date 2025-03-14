package github.hyungi.web.product.api;

import github.hyungi.annotation.JwtAuthentication;
import github.hyungi.domain.product.model.Product;
import github.hyungi.domain.product.service.AddProductService;
import github.hyungi.web.product.model.request.AddProductRequest;
import github.hyungi.web.product.model.response.ProductResponse;
import github.hyungi.web.product.model.response.wrapper.ProductResponseWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductApiController {
    private final Path uploadPath;
    private final AddProductService addProductService;

    public ProductApiController(@Value("${image.upload-dir}") String imageUploadDir, AddProductService addProductService) {
        this.uploadPath = Paths.get(imageUploadDir).toAbsolutePath().normalize();
        this.addProductService = addProductService;

        if (!uploadPath.toFile().exists()) {
            uploadPath.toFile().mkdirs();
        }
    }

    @PutMapping("/product-add")
    public ProductResponseWrapper addProduct(
            @JwtAuthentication UUID userId,
            @RequestPart("image") MultipartFile image,
            @RequestPart("product") AddProductRequest addProductRequest
    ) throws IOException {

        String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path imageFilePath = uploadPath.resolve(fileName);

        image.transferTo(imageFilePath.toFile()); // 파일 저장

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
