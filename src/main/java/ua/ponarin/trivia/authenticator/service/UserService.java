package ua.ponarin.trivia.authenticator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ponarin.trivia.authenticator.api.UserApiClient;
import ua.ponarin.trivia.authenticator.model.User;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Integer FIRST_ELEMENT = 0;
    private final UserApiClient userApiClient;

    public User findByLoginAndPassword(String login, String password) {
        var users = userApiClient.findByLoginAndPassword(login, password);
        if (users.size() == 0) {
            throw new NoSuchElementException(String.format("User with login '%s' and provided password was not found", login));
        } else if (users.size() > 1) {
            throw new IllegalArgumentException(String.format("There are more than one user with login '%s' and provided password", login));
        } else {
            return users.get(FIRST_ELEMENT);
        }
    }
}
