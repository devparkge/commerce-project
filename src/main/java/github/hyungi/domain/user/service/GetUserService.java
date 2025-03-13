package github.hyungi.domain.user.service;

import github.hyungi.domain.user.model.User;
import github.hyungi.domain.user.repository.UsersRepository;
import github.hyungi.exception.NotFoundUUIDException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final UsersRepository usersRepository;

    public User findById(UUID userId) {
        return usersRepository.findById(userId).orElseThrow(NotFoundUUIDException::new);
    }
}
