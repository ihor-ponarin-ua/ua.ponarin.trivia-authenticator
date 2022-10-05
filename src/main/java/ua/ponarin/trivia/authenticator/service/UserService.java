package ua.ponarin.trivia.authenticator.service;

import org.springframework.stereotype.Service;
import ua.ponarin.trivia.authenticator.model.User;

import java.util.List;

@Service
public class UserService {
    private static final List<User> USERS = List.of(
            new User(1L, "admin", "123", "ADMIN"),
            new User(2L, "userA", "123", "USER"),
            new User(3L, "userB", "123", "USER")
    );

    public User findByLoginAndPassword(String login, String password) {
        return USERS.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .orElseThrow();
    }
}
