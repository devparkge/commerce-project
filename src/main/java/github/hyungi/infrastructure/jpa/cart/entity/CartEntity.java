package github.hyungi.infrastructure.jpa.cart.entity;

import github.hyungi.domain.cart.model.Cart;
import github.hyungi.infrastructure.jpa.users.entity.UsersEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class CartEntity {
    @Id
    private UUID cartId;
    @OneToOne
    @JoinColumn(name = "cartHostId")
    private UsersEntity cartHostEntity;
    @OneToMany(mappedBy = "cartEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItemEntities = new ArrayList<>();
    private BigDecimal totalAmount;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public CartEntity(UUID cartId, UsersEntity cartHostEntity, List<CartItemEntity> cartItemEntities, BigDecimal totalAmount) {
        this.cartId = cartId;
        this.cartHostEntity = cartHostEntity;
        this.cartItemEntities = cartItemEntities;
        this.cartItemEntities.forEach(cartItemEntity -> cartItemEntity.setCartEntity(this));
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return Objects.equals(cartId, that.cartId) && Objects.equals(cartHostEntity, that.cartHostEntity) && Objects.equals(cartItemEntities, that.cartItemEntities) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, cartHostEntity, cartItemEntities, totalAmount, createdAt, updatedAt);
    }

    public Cart toDomain() {
        return new Cart(
                cartId,
                cartHostEntity.toDomain(),
                cartItemEntities.stream().map(CartItemEntity::toDomain).toList(),
                totalAmount,
                createdAt,
                updatedAt
        );
    }

    public static CartEntity from(Cart cart, UsersEntity cartHostEntity, List<ItemEntity> cartItemEntities) {
        return new CartEntity(
                cart.cartId(),
                cartHostEntity,
                cartItemEntities.stream()
                        .map(CartItemEntity::from)
                        .toList(),
                cart.totalAmount()
        );
    }
}
