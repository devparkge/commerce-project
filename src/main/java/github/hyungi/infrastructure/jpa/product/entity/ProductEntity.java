package github.hyungi.infrastructure.jpa.product.entity;

import github.hyungi.domain.product.model.Product;
import github.hyungi.infrastructure.jpa.users.entity.UsersEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ProductEntity {
    @Id
    private UUID productId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellerId")
    private UsersEntity sellerEntity;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private String imageUrl;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public ProductEntity(UUID productId, UsersEntity sellerEntity, String name, String description, BigDecimal price, Integer stock, String category, String imageUrl) {
        this.productId = productId;
        this.sellerEntity = sellerEntity;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(productId, that.productId) && Objects.equals(sellerEntity, that.sellerEntity) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(stock, that.stock) && Objects.equals(category, that.category) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sellerEntity, name, description, price, stock, category, imageUrl, createdAt, updatedAt);
    }

    public Product toDomain() {
        return Product.create(
                sellerEntity.toDomain(),
                name,
                description,
                price,
                stock,
                category,
                imageUrl
        );
    }

    public static ProductEntity from(Product product, UsersEntity sellerEntity) {
        return new ProductEntity(
                product.productId(),
                sellerEntity,
                product.name(),
                product.description(),
                product.price(),
                product.stock(),
                product.category(),
                product.imageUrl()
        );
    }
}
