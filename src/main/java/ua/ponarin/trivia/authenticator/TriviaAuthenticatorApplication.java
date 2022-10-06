package ua.ponarin.trivia.authenticator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TriviaAuthenticatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TriviaAuthenticatorApplication.class, args);
    }

}
