package github.hyungi.domain.service;

import github.hyungi.domain.model.User;
import github.hyungi.domain.repository.UsersRepository;
import github.hyungi.exception.InvalidPasswordException;
import github.hyungi.exception.NotFoundEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService {
    private final UsersRepository usersRepository;

    public User login(String email, String password) {
        User user = usersRepository.findByEmail(email)
                .orElseThrow(NotFoundEmailException::new);
        if(user.matchesPassword(password)) {
            return user;
        } else {
            throw new InvalidPasswordException();
        }
    }
}
