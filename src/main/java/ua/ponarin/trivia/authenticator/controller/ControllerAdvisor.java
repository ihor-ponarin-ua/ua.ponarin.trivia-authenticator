package ua.ponarin.trivia.authenticator.controller;

import feign.FeignException;
import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class ControllerAdvisor {
    @ExceptionHandler({
            FeignException.NotFound.class,
            JwtException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleUnauthorizedExceptions(Exception ex) {
        log.warn("Authorization failed: {}", ex.getMessage());
    }
}
