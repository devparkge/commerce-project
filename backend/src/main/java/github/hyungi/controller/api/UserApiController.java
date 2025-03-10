package github.hyungi.controller.api;

import github.hyungi.controller.model.request.SignUpRequest;
import github.hyungi.controller.model.response.wrapper.UserResponseWrapper;
import github.hyungi.domain.model.User;
import github.hyungi.domain.service.SignUpUserService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApiController {
    private final SignUpUserService signUpUserService;

    @Update("/signup")
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
