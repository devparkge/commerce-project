package github.hyungi.infrastructure.jpa.cart.entity;

import github.hyungi.domain.cart.model.Item;
import github.hyungi.infrastructure.jpa.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ItemEntity {
    @Id
    private UUID itemId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;
    private BigDecimal totalPrice;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public ItemEntity(UUID itemId, ProductEntity productEntity, BigDecimal totalPrice) {
        this.itemId = itemId;
        this.productEntity = productEntity;
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(productEntity, that.productEntity) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, productEntity, totalPrice, createdAt, updatedAt);
    }

    public Item toDomain() {
        return new Item(
                itemId,
                productEntity.toDomain(),
                totalPrice,
                createdAt,
                updatedAt
        );
    }

    public static ItemEntity from(Item item, ProductEntity productEntity) {
        return new ItemEntity(
                item.itemId(),
                productEntity,
                item.totalPrice()
        );
    }
}
