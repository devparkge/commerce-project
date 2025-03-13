package github.hyungi.annotation.resolver;

import github.hyungi.annotation.JwtAuthentication;
import github.hyungi.domain.user.service.AuthenticationUserService;
import github.hyungi.exception.InvalidTokenException;
import github.hyungi.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthenticationResolver implements HandlerMethodArgumentResolver {
    private final AuthenticationUserService authenticationUserService;
    private final String header;
    private final String tokenPrefix;
    private final JwtUtil jwtUtil;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JwtAuthentication.class) != null;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) throws Exception {
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        return extractToken(httpServletRequest)
                .map(token -> jwtUtil.parseToken(token))
                .filter(authenticationUserService::authenticationUserById)
                .orElseThrow(InvalidTokenException::new);
    }

    private Optional<String> extractToken(HttpServletRequest httpServletRequest) {
        return Optional.ofNullable(httpServletRequest.getHeader(header))
                .filter(header -> header.startsWith(tokenPrefix))
                .map(header -> header.replaceAll(tokenPrefix, ""));
    }
}
