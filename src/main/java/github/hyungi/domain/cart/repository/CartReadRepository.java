package github.hyungi.domain.cart.repository;

import github.hyungi.domain.cart.model.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartReadRepository {
    Optional<Cart> findByCartHostId(UUID cartHostId);
}
