package github.hyungi.web.model.request;

public record LoginRequest(
        String email,
        String password
) {
}
