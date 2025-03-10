package github.hyungi.web.api;

import github.hyungi.domain.model.User;
import github.hyungi.domain.service.SignUpUserService;
import github.hyungi.web.model.request.SignUpRequest;
import github.hyungi.web.model.response.wrapper.UserResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {
    private final SignUpUserService signUpUserService;

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
        return UserResponseWrapper.from(user);
    }
}
