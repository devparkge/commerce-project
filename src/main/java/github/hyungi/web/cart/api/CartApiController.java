package github.hyungi.web.cart.api;

import github.hyungi.annotation.JwtAuthentication;
import github.hyungi.domain.cart.service.AddProductToCartService;
import github.hyungi.web.cart.model.request.AddProductToCartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CartApiController {
    private final AddProductToCartService addProductToCartService;

    @PutMapping("/products/{productId}")
    public void addProductToCart(
            @JwtAuthentication UUID authUserUUID,
            @PathVariable("productId") UUID productId,
            @RequestBody AddProductToCartRequest addProductToCartRequest
    ) {
        addProductToCartService.addProductToCart(authUserUUID, productId, addProductToCartRequest.stock());
    }
}
