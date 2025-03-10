package github.hyungi.web.model.request;

public record SignUpRequest(
        String email,
        String username,
        String password,
        String phoneNumber,
        String address
) {
}
