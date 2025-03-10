package github.hyungi.web.model.response.wrapper;

import github.hyungi.web.model.response.UserResponse;
import github.hyungi.domain.model.User;

public record UserResponseWrapper(
        UserResponse user
) {
    public static UserResponseWrapper from(User user) {
        return new UserResponseWrapper(UserResponse.from(user));
    }
}
