package github.hyungi.web.users.model.response;

import github.hyungi.domain.user.model.User;

import java.util.UUID;

public record UserResponse (
        UUID userId,
        String token,
        String email,
        String username,
        String phoneNumber,
        String address
) {
    public static UserResponse from(User user, String token) {
        return new UserResponse(
                user.userId(),
                token,
                user.email(),
                user.username(),
                user.phoneNumber(),
                user.address()
        );
    }
}
