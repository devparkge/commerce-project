package github.hyungi.infrastructure.jpa.cart.entity;

import github.hyungi.domain.cart.model.Item;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Entity(name = "cart_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class CartItemEntity {
    @Id
    @Column(name = "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer databaseId;
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    private CartEntity cartEntity;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "itemId")
    private ItemEntity itemEntity;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public CartItemEntity(Integer databaseId, ItemEntity itemEntity) {
        this.databaseId = databaseId;
        this.itemEntity = itemEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemEntity that = (CartItemEntity) o;
        return Objects.equals(databaseId, that.databaseId) && Objects.equals(cartEntity, that.cartEntity) && Objects.equals(itemEntity, that.itemEntity) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(databaseId, cartEntity, itemEntity, createdAt, updatedAt);
    }

    public Item toDomain() {
        return itemEntity.toDomain();
    }

    public static CartItemEntity from(ItemEntity itemEntity) {
        ItemEntity itemEntity1 = itemEntity;
        return new CartItemEntity(
                null,
                itemEntity
        );
    }
}
