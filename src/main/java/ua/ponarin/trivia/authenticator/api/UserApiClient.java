package ua.ponarin.trivia.authenticator.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.ponarin.trivia.authenticator.model.User;

import javax.validation.constraints.NotBlank;
import java.util.List;

@FeignClient(name = "userApiClient", url = "${api.users}")
@Validated
public interface UserApiClient {
    @GetMapping("/v1/users")
    List<User> findByLoginAndPassword(
            @NotBlank @RequestParam String login,
            @NotBlank @RequestParam String password
    );
}
