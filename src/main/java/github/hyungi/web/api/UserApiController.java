package github.hyungi.web.api;

import github.hyungi.domain.user.model.User;
import github.hyungi.domain.user.service.LoginService;
import github.hyungi.domain.user.service.SignUpUserService;
import github.hyungi.web.model.UserResponseAssembler;
import github.hyungi.web.model.request.LoginRequest;
import github.hyungi.web.model.request.SignUpRequest;
import github.hyungi.web.model.response.wrapper.UserResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {
    private final SignUpUserService signUpUserService;
    private final UserResponseAssembler userResponseAssembler;
    private final LoginService loginService;

    @PostMapping("/login")
    public UserResponseWrapper login(
            @RequestBody LoginRequest loginRequest
    ) {
        User user = loginService.login(loginRequest.email(), loginRequest.password());
        UUID userId = user.userId();
        return userResponseAssembler.assembleUserResponse(user, userId);
    }

    @PutMapping("/signup")
    public UserResponseWrapper signUp(
            @RequestBody SignUpRequest signUpRequest
    ) {
        User user = signUpUserService.signUp(
                signUpRequest.email(),
                signUpRequest.username(),
                signUpRequest.password(),
                signUpRequest.phoneNumber(),
                signUpRequest.address()
        );
        return userResponseAssembler.assembleUserResponse(user, null);
    }
}
