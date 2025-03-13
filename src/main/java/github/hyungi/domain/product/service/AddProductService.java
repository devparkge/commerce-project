package github.hyungi.domain.product.service;

import github.hyungi.domain.product.model.Product;
import github.hyungi.domain.product.repository.ProductRepository;
import github.hyungi.domain.user.model.User;
import github.hyungi.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddProductService {
    private final ProductRepository productRepository;
    private final GetUserService getUserService;

    public Product addProduct(
            UUID userId,
            String name,
            String description,
            BigDecimal price,
            int stock,
            String category,
            String imageUrl
    ){
        User seller = getUserService.findById(userId);
        return productRepository.save(
                Product.create(
                        seller,
                        name,
                        description,
                        price,
                        stock,
                        category,
                        imageUrl
                )
        );
    }
}
