package ua.ponarin.trivia.authenticator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.ponarin.trivia.authenticator.api.UserApiClient;
import ua.ponarin.trivia.authenticator.model.JwtToken;
import ua.ponarin.trivia.authenticator.model.UserRequestDto;
import ua.ponarin.trivia.authenticator.service.JwtService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticatorController {
    private final UserApiClient userApiClient;
    private final JwtService jwtService;

    @PostMapping("/token")
    public JwtToken generateToken(@Valid @RequestBody UserRequestDto userRequestDto) {
        var user = userApiClient.findByLoginAndPassword(userRequestDto.getLogin(), userRequestDto.getPassword());
        var token = jwtService.generateToken(user);
        return new JwtToken(token);
    }

    @PostMapping("/validate")
    public JwtToken validateToken(@Valid @RequestBody JwtToken jwtToken) {
        jwtService.validateToken(jwtToken.getToken());
        return jwtToken;
    }
}
