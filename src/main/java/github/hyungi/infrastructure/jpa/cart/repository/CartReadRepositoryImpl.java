package github.hyungi.infrastructure.jpa.cart.repository;

import github.hyungi.domain.cart.model.Cart;
import github.hyungi.domain.cart.repository.CartReadRepository;
import github.hyungi.infrastructure.jpa.cart.dao.CartDao;
import github.hyungi.infrastructure.jpa.cart.entity.CartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CartReadRepositoryImpl implements CartReadRepository {
    private final CartDao cartDao;

    @Override
    public Optional<Cart> findByCartHostId(UUID cartHostId) {
        return cartDao.findByCartHostId(cartHostId).map(CartEntity::toDomain);
    }
}
