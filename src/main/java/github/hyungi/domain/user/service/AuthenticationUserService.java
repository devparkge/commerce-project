package github.hyungi.domain.user.service;

import github.hyungi.domain.user.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationUserService {
    private final UsersRepository usersRepository;

    public boolean authenticationUserById(UUID userId) {
        return usersRepository.findById(userId).isPresent();
    }
}
