package ua.ponarin.trivia.authenticator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ponarin.trivia.authenticator.model.JwtToken;
import ua.ponarin.trivia.authenticator.model.UserRequest;
import ua.ponarin.trivia.authenticator.service.JwtService;
import ua.ponarin.trivia.authenticator.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/authenticator")
@RequiredArgsConstructor
public class JwtAuthenticatorController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/token")
    public JwtToken generateToken(@Valid @RequestBody UserRequest userRequest) {
        var user = userService.findByLoginAndPassword(userRequest.getLogin(), userRequest.getPassword());
        var token = jwtService.generateToken(user);
        return new JwtToken(token);
    }

    @PostMapping("/validate")
    public JwtToken validateToken(@Valid @RequestBody JwtToken jwtToken) {
        jwtService.validateToken(jwtToken.getToken());
        return jwtToken;
    }
}
