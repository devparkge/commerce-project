package github.hyungi.domain.cart.repository;

import github.hyungi.domain.cart.model.Cart;

public interface CartRepository extends CartReadRepository{
    Cart save(Cart cart);
}
