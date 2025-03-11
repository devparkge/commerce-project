package github.hyungi.web.model;

import github.hyungi.domain.model.User;
import github.hyungi.util.JwtUtil;
import github.hyungi.web.model.response.UserResponse;
import github.hyungi.web.model.response.wrapper.UserResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserResponseAssembler {
    private final JwtUtil jwtUtil;

    public UserResponseWrapper assembleUserResponse(User user, UUID authUserUUID) {
        String token = (authUserUUID != null) ? jwtUtil.generateToken(authUserUUID) : null;
        return UserResponseWrapper.create(
                UserResponse.from(user, token)
        );
    }
}
