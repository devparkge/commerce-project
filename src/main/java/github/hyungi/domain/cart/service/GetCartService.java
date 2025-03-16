package github.hyungi.domain.cart.service;

import github.hyungi.domain.cart.model.Cart;
import github.hyungi.domain.cart.repository.CartRepository;
import github.hyungi.exception.NotFoundUUIDException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCartService {
    private final CartRepository cartRepository;

    public Cart getCart(UUID cartHostId){
        return cartRepository.findByCartHostId(cartHostId).orElseThrow(NotFoundUUIDException::new);
    }
}
