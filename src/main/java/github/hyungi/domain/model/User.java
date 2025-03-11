package github.hyungi.domain.model;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record User(
        UUID userId,
        String email,
        String username,
        String password,
        String phoneNumber,
        String address,
        Instant createdAt,
        Instant updatedAt
) {
    public static User create(
            UUID userId,
            String email,
            String username,
            String password,
            String phoneNumber,
            String address
    ) {
        return builder()
                .userId(userId)
                .email(email)
                .username(username)
                .password(password)
                .phoneNumber(phoneNumber)
                .address(address)
                .build();
    }

    public boolean matchesPassword(String password) {
        return password().equals(password);
    }
}
