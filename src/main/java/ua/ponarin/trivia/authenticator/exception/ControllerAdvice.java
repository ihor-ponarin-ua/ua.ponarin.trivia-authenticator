package ua.ponarin.trivia.authenticator.exception;

import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Log4j2
public class ControllerAdvice {
    @ExceptionHandler({
            NoSuchElementException.class,
            JwtException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedExceptions(Exception exception) {
        log.warn("Authorization failed: {}", exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleInternalServerError(Exception exception) {
        log.error("Internal server error:", exception);
        return exception.getMessage();
    }

}
