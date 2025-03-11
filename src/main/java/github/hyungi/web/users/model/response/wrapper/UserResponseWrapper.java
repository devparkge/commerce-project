package github.hyungi.web.users.model.response.wrapper;

import github.hyungi.web.users.model.response.UserResponse;

public record UserResponseWrapper(
        UserResponse user
) {
    public static UserResponseWrapper create(UserResponse user) {
        return new UserResponseWrapper(user);
    }
}
