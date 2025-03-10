package github.hyungi.infrastructure.jpa.entity;

import github.hyungi.domain.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UsersEntity {
    @Id
    private UUID userId;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    public UsersEntity(UUID userId, String email, String username, String password, String phoneNumber, String address) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return Objects.equals(userId, that.userId) && Objects.equals(email, that.email) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, username, password, phoneNumber, address, createdAt, updatedAt);
    }

    public User toDomain() {
        return new User(
                userId,
                email,
                username,
                password,
                phoneNumber,
                address,
                createdAt,
                updatedAt
        );
    }

    public static UsersEntity from(User user) {
        return new UsersEntity(
                user.userId(),
                user.email(),
                user.username(),
                user.password(),
                user.phoneNumber(),
                user.address()
        );
    }
}
