package github.hyungi.controller.model.response.wrapper;

import github.hyungi.controller.model.response.UserResponse;
import github.hyungi.domain.model.User;

public record UserResponseWrapper(
        UserResponse user
) {
    public static UserResponseWrapper from(User user) {
        return new UserResponseWrapper(UserResponse.from(user));
    }
}
