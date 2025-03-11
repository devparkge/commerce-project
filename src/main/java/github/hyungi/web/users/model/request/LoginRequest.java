package github.hyungi.web.users.model.request;

public record LoginRequest(
        String email,
        String password
) {
}
