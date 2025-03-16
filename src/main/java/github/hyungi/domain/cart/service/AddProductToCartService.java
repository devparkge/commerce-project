package github.hyungi.domain.cart.service;

import github.hyungi.domain.cart.model.Cart;
import github.hyungi.domain.cart.model.Item;
import github.hyungi.domain.cart.repository.CartRepository;
import github.hyungi.domain.product.model.Product;
import github.hyungi.domain.product.service.GetProductService;
import github.hyungi.domain.user.model.User;
import github.hyungi.domain.user.service.GetUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddProductToCartService {
    private final CartRepository cartRepository;
    private final GetUserService getUserService;
    private final GetProductService getProductService;

    public void addProductToCart(UUID userId, UUID productId, int stock) {
        User buyer = getUserService.findById(userId);
        Product product = getProductService.getProduct(productId);
        Item item = Item.create(
                product,
                stock
        );
        Cart newCart = cartRepository.findByCartHostId(userId).map(cart -> cart.updateItemList(item)).orElseGet(() -> Cart.create(buyer, List.of(item)));
        cartRepository.save(newCart);
}
    }
