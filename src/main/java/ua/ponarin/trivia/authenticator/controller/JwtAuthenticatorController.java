package ua.ponarin.trivia.authenticator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.ponarin.trivia.authenticator.model.JwtToken;
import ua.ponarin.trivia.authenticator.model.UserRequestDto;
import ua.ponarin.trivia.authenticator.service.UserService;
import ua.ponarin.trivia.authenticator.util.JwtUtils;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticatorController {
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/token")
    public JwtToken generateToken(@Valid @RequestBody UserRequestDto userRequestDto) {
        var user = userService.findByLoginAndPassword(userRequestDto.getLogin(), userRequestDto.getPassword());
        var token = jwtUtils.generateToken(user);
        return new JwtToken(token);
    }

    @PostMapping("/validate")
    public JwtToken validateToken(@Valid @RequestBody JwtToken jwtToken) {
        jwtUtils.validateToken(jwtToken.getToken());
        return jwtToken;
    }
}
