package github.hyungi.config;

import github.hyungi.annotation.resolver.JwtAuthenticationResolver;
import github.hyungi.domain.user.service.AuthenticationUserService;
import github.hyungi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationUserService authenticationUserService;
    private final String header;
    private final String tokenPrifix;
    private final JwtUtil jwtUtil;

    public WebConfig(
            AuthenticationUserService authenticationUserService,
            @Value("${jwt.header}") String header,
            @Value("${jwt.token-prefix}") String tokenPrifix,
            JwtUtil jwtUtil
    ) {
        this.authenticationUserService = authenticationUserService;
        this.header = header;
        this.tokenPrifix = tokenPrifix;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new JwtAuthenticationResolver(
                authenticationUserService,
                header,
                tokenPrifix,
                jwtUtil
        ));
    }
}
