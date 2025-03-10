package github.hyungi.domain.service;

import github.hyungi.domain.model.User;
import github.hyungi.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpUserService {
    private final UsersRepository usersRepository;

    public User signUp(String email, String username, String password, String phoneNumber, String address) {
        return usersRepository.save(
                User.create(
                        UUID.randomUUID(),
                        email,
                        username,
                        password,
                        phoneNumber,
                        address
                )
        );
    }
}
