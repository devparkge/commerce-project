package github.hyungi.infrastructure.jpa.cart.dao;

import github.hyungi.infrastructure.jpa.cart.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartDao extends JpaRepository<CartEntity, UUID> {
    @Query("""
            SELECT c
            FROM cart c
                INNER JOIN c.cartHostEntity ch
            WHERE ch.userId = :cartHostId
            """)
    Optional<CartEntity> findByCartHostId(@Param("cartHostId") UUID cartHostId);
}
